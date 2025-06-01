package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Country;
import com.example.demo.mongoModel.MongoCountry;
import com.example.demo.mongoRepository.MCountryRepository;
import com.example.demo.repositories.CountryRepository;

@Service
public class CountryService {
    
    @Autowired 
    private CountryRepository repository;

    @Autowired
    private MCountryRepository mRepository;

    public List<Country> findAll() { return repository.findAll(); }


    public Country create(Country country) {
        mRepository.save(new MongoCountry(country.getCode(), country.getName()));
        return repository.save(country);
    }
}
