package com.example.demo.RestControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Subject;
import com.example.demo.services.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    
    @Autowired 
    private SubjectService service;
    @GetMapping public List<Subject> list() { return service.findAll(); }
}