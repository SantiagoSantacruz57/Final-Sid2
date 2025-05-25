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
@Table(name = "countries")
public class Country {
    @Id
    private Integer code;

    private String name;
}