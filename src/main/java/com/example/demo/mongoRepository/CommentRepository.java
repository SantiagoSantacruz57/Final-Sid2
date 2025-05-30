package com.example.demo.mongoRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.mongoModel.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

    
}
