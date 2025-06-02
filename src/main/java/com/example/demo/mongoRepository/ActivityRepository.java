package com.example.demo.mongoRepository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.mongoModel.Activity;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> findBySubjectId(String subjectId);
}
