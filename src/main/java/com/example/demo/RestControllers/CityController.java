package com.example.demo.RestControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.City;
import com.example.demo.services.CityService;


@RestController
@RequestMapping("/cities")
public class CityController {
    
    @Autowired 
    private CityService service;
    @GetMapping public List<City> list() { return service.findAll(); }
}
