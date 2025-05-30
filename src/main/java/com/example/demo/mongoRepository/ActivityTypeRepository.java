package com.example.demo.mongoRepository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.mongoModel.ActivityType;

public  interface ActivityTypeRepository extends MongoRepository<ActivityType, String> {
    ActivityType findByName(String name);
    List<ActivityType> findAll();
}
