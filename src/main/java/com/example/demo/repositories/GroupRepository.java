package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {}