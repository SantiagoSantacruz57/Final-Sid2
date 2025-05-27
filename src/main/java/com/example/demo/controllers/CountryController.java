package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Country;
import com.example.demo.services.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {
    
    @Autowired 
    private CountryService service;

    @GetMapping public List<Country> list() { return service.findAll(); }


    @PostMapping
    public Country create(@RequestBody Country country) {
        return service.create(country);
    }
}