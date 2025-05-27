package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Country;
import com.example.demo.repositories.CountryRepository;

@Service
public class CountryService {
    
    @Autowired 
    private CountryRepository repository;
    public List<Country> findAll() { return repository.findAll(); }
}
