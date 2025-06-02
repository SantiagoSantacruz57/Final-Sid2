package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserSubject;
import com.example.demo.model.User;
import com.example.demo.model.UserSubjectId;

@Repository
public interface UserSubjectRepository extends JpaRepository<UserSubject, UserSubjectId> {
    List<UserSubject> findByUser(User user);
} 