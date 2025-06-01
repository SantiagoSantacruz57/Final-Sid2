package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Group;
import com.example.demo.repositories.GroupRepository;

@Service
public class GroupService {
    
    @Autowired 
    private GroupRepository repository;
    public List<Group> findAll() { return repository.findAll(); }
}