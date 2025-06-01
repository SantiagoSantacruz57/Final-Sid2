package com.example.demo.RestControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Faculty;
import com.example.demo.services.FacultyService;

@RestController
@RequestMapping("/faculties")
public class FacultyController {
    
    @Autowired 
    private FacultyService service;
    @GetMapping public List<Faculty> list() { return service.findAll(); }
}