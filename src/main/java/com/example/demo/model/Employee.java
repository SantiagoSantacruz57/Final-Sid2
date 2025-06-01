package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "contract_type")
    private ContractType contractType;

    @ManyToOne
    @JoinColumn(name = "employee_type")
    private EmployeeType employeeType;

    @ManyToOne
    @JoinColumn(name = "faculty_code")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "campus_code")
    private Campus campus;

    @ManyToOne
    @JoinColumn(name = "birth_place_code")
    private City birthPlace;
}