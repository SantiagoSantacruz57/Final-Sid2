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
@Table(name = "areas")
public class Area {
    @Id
    private Integer code;

    private String name;

    @ManyToOne
    @JoinColumn(name = "faculty_code")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private Employee coordinator;
}