package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Area;
import com.example.demo.repositories.AreaRepository;

@Service
public class AreaService {
    
    @Autowired 
    private AreaRepository repository;
    public List<Area> findAll() { return repository.findAll(); }
}