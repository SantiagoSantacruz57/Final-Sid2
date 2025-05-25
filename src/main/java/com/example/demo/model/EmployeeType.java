package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_types")
public class EmployeeType {
    @Id
    private String name;
}