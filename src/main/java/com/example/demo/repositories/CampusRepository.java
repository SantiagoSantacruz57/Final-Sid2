package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Campus;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Integer> {}