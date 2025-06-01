package com.example.demo.mongoRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.mongoModel.EvalPlan;

public interface EvalPlanRepository extends MongoRepository<EvalPlan, String> {
}
