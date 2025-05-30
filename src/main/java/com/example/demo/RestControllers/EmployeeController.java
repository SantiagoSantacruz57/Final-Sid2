package com.example.demo.RestControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired 
    private EmployeeService service;
    @GetMapping public List<Employee> list() { return service.findAll(); }
}