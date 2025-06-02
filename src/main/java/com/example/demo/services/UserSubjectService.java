package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.model.UserSubject;
import com.example.demo.repositories.UserSubjectRepository;

@Service
public class UserSubjectService {
    
    @Autowired
    private UserSubjectRepository repository;

    public List<Subject> getSubjectsByUser(User user) {
        return repository.findByUser(user)
            .stream()
            .map(UserSubject::getSubject)
            .collect(Collectors.toList());
    }

    public void enrollUserInSubject(User user, Subject subject) {
        UserSubject userSubject = new UserSubject(user, subject);
        repository.save(userSubject);
    }

    public void unenrollUserFromSubject(User user, Subject subject) {
        UserSubject userSubject = new UserSubject(user, subject);
        repository.delete(userSubject);
    }
} 