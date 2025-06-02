package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Subject;
import com.example.demo.repositories.SubjectRepository;

@Service
public class SubjectService {
    
    @Autowired
    private SubjectRepository repository;

    public List<Subject> getAllSubjects() {
        return repository.findAll();
    }

    public Subject getSubjectByCode(String code) {
        return repository.findById(code)
            .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
    }
}
