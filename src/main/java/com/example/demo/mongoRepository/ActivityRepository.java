package com.example.demo.mongoRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.mongoModel.Activity;

public interface ActivityRepository extends MongoRepository<Activity, String> {

}
