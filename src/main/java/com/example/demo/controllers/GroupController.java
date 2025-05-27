package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Group;
import com.example.demo.services.GroupService;

@RestController
@RequestMapping("/groups")
public class GroupController {
    
    @Autowired 
    private GroupService service;
    @GetMapping public List<Group> list() { return service.findAll(); }
}