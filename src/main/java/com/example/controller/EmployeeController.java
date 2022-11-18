package com.example.controller;

import com.example.clientInterface.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController("http://localhost:8082")
public class EmployeeController {

    @Autowired
    EmployeeClient client;
    @GetMapping(path = "retrieve/{id}")
    ResponseEntity<Object> getEmployeeById(@PathVariable Long id)
    {
        var response = client.getEmployeeById(id);
        return ResponseEntity.ok().body(response.getBody());
    }

}
