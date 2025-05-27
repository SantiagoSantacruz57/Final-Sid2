package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Program;
import com.example.demo.repositories.ProgramRepository;

@Service
public class ProgramService {
    
    @Autowired 
    private ProgramRepository repository;
    public List<Program> findAll() { return repository.findAll(); }
}