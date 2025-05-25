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
@Table(name = "programs")
public class Program {
    @Id
    private Integer code;

    private String name;

    @ManyToOne
    @JoinColumn(name = "area_code")
    private Area area;
}