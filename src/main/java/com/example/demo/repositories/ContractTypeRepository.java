package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ContractType;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, String> {}