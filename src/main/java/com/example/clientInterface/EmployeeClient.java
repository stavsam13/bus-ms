package com.example.clientInterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.util.BusConstants;

@FeignClient(name = BusConstants.EMPLOYEE_SERVICE, url = BusConstants.EMPLOYEE_URL)
public interface EmployeeClient {

    @GetMapping("retrieve/{id}")
    ResponseEntity<Object> getEmployeeById(@PathVariable Long id);
}
