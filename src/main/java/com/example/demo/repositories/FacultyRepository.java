package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {}
