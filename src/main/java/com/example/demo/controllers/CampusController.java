package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Campus;
import com.example.demo.services.CampusService;

@RestController
@RequestMapping("/campuses")
public class CampusController {
    
    @Autowired 
    private CampusService service;
    @GetMapping public List<Campus> list() { return service.findAll(); }
}
