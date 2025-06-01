package com.example.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.mongoModel.Activity;
import com.example.demo.mongoModel.ActivityType;
import com.example.demo.mongoModel.Comment;
import com.example.demo.mongoModel.EvalPlan;
import com.example.demo.mongoModel.Grade;
import com.example.demo.mongoRepository.ActivityRepository;
import com.example.demo.mongoRepository.ActivityTypeRepository;
import com.example.demo.mongoRepository.CommentRepository;
import com.example.demo.mongoRepository.EvalPlanRepository;
import com.example.demo.mongoRepository.GradeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private EvalPlanRepository evalPlanRepo;

    @Autowired
    private ActivityRepository activityRepo;

    @Autowired
    private ActivityTypeRepository activityTypeRepo;

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private GradeRepository gradeRepo;

    private final ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void loadData() {
        try {
            // Clear collections first
            evalPlanRepo.deleteAll();
            activityRepo.deleteAll();
            activityTypeRepo.deleteAll();
            commentRepo.deleteAll();
            gradeRepo.deleteAll();

            // Load from JSON
            List<EvalPlan> plans = Arrays.asList(
                mapper.readValue(new File("src/main/resources/evalplans.json"), EvalPlan[].class)
            );
            List<Activity> activities = Arrays.asList(
                mapper.readValue(new File("src/main/resources/activities.json"), Activity[].class)
            );
            List<ActivityType> activityTypes = Arrays.asList(
                mapper.readValue(new File("src/main/resources/activitytypes.json"), ActivityType[].class)
            );
            List<Comment> comments = Arrays.asList(
                mapper.readValue(new File("src/main/resources/comments.json"), Comment[].class)
            );
            List<Grade> grades = Arrays.asList(
                mapper.readValue(new File("src/main/resources/grades.json"), Grade[].class)
            );

            // Save them
            evalPlanRepo.saveAll(plans);
            activityRepo.saveAll(activities);
            activityTypeRepo.saveAll(activityTypes);
            commentRepo.saveAll(comments);
            gradeRepo.saveAll(grades);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
