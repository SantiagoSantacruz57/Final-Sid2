package com.example.demo.RestControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Program;
import com.example.demo.services.ProgramService;

@RestController
@RequestMapping("/programs")
public class ProgramController {
    
    @Autowired 
    private ProgramService service;
    @GetMapping public List<Program> list() { return service.findAll(); }
}