package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.City;
import com.example.demo.repositories.CityRepository;

@Service
public class CityService {
    
    @Autowired 
    private CityRepository repository;
    public List<City> findAll() { return repository.findAll(); }
}