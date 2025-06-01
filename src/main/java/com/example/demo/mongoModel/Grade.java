package com.example.demo.mongoModel;


import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "grades")
public class Grade {
    @Id
    private String id;

    private String activityId;  

    private int score;    
}
