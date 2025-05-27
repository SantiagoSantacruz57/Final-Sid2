package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Area;
import com.example.demo.services.AreaService;


@RestController
@RequestMapping("/areas")
public class AreaController {
    
    @Autowired 
    private AreaService service;
    @GetMapping public List<Area> list() { return service.findAll(); }
}
