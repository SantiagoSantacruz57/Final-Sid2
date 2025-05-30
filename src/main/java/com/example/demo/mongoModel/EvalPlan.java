package com.example.demo.mongoModel;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "eval_plans")
public class EvalPlan {
    @Id
    private String id;
    private String subjectId;
    private String authorId;
    private String name;

    private List<String> likes = new ArrayList<>(); // list of user IDs

    private List<String> activityIds; // References to Activities
    private List<String> commentIds; // References to Comments

    private String createdAt;  // ISO-8601 timestamp

        // These are not persisted — just used for returning full details
    @Transient
    private List<Activity> activities;

    @Transient
    private List<Comment> comments;


}
