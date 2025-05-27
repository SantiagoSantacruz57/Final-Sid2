package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ContractType;
import com.example.demo.services.ContractTypeService;

@RestController
@RequestMapping("/contract-types")
public class ContractTypeController {
    
    @Autowired 
    private ContractTypeService service;
    @GetMapping public List<ContractType> list() { return service.findAll(); }
}