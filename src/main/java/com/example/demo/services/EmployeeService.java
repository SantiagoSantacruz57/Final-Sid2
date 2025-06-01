package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired 
    private EmployeeRepository repository;
    public List<Employee> findAll() { return repository.findAll(); }
}