package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {}