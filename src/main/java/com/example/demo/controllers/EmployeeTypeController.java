package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmployeeType;
import com.example.demo.services.EmployeeTypeService;

@RestController
@RequestMapping("/employee-types")
public class EmployeeTypeController {
    
    @Autowired 
    private EmployeeTypeService service;
    @GetMapping public List<EmployeeType> list() { return service.findAll(); }
}