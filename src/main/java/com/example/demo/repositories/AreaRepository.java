package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {}
