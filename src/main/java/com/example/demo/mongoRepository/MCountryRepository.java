package com.example.demo.mongoRepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.mongoModel.MongoCountry;

@Repository
public interface MCountryRepository extends MongoRepository<MongoCountry, Integer> {
    MongoCountry findByName(String name);
}
