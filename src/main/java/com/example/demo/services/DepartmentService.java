package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.repositories.DepartmentRepository;

@Service
public class DepartmentService {
    
    @Autowired 
    private DepartmentRepository repository;
    public List<Department> findAll() { return repository.findAll(); }
}