package com.example.demo.mongoModel;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String user;
    private String content;
}
