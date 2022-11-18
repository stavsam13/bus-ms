package com.example.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class FeignConfiguration implements Filter {

    private static Logger Log = LoggerFactory.getLogger(FeignConfiguration.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain
            filterChain)throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req, res);
        } else {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("Missing or invalid Authorization header");
            }
            final String token = authHeader.substring(7);
            final Claims claims = Jwts.parser()
                    .setSigningKey("secretkey")
                    .parseClaimsJws(token)
                    .getBody();
            request.setAttribute("claims", claims);
            filterChain.doFilter(req, res);
        }
    }
}