package com.example.demo.mongoRepository;

import com.example.demo.mongoModel.Grade;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GradeRepository extends MongoRepository<Grade, String> {
}

