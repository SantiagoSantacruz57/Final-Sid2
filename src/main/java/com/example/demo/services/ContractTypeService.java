package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ContractType;
import com.example.demo.repositories.ContractTypeRepository;

@Service
public class ContractTypeService {
    
    @Autowired 
    private ContractTypeRepository repository;
    public List<ContractType> findAll() { return repository.findAll(); }
}