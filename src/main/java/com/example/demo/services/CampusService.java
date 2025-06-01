package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Campus;
import com.example.demo.repositories.CampusRepository;

@Service
public class CampusService {
    
    @Autowired 
    private CampusRepository repository;
    public List<Campus> findAll() { return repository.findAll(); }
}