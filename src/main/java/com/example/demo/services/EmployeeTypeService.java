package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmployeeType;
import com.example.demo.repositories.EmployeeTypeRepository;

@Service
public class EmployeeTypeService {
    
    @Autowired 
    private EmployeeTypeRepository repository;
    public List<EmployeeType> findAll() { return repository.findAll(); }
}