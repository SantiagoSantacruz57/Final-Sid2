package com.example.demo.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.mongoModel.Activity;
import com.example.demo.mongoModel.Comment;
import com.example.demo.mongoModel.EvalPlan;
import com.example.demo.mongoRepository.ActivityRepository;
import com.example.demo.mongoRepository.CommentRepository;
import com.example.demo.mongoRepository.EvalPlanRepository;

import java.util.List;

@RestController
@RequestMapping("/evalplans")
public class EvalPlanController {

    @Autowired
    private EvalPlanRepository evalPlanRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private CommentRepository commentRepository;

    // Get all eval plans
    @GetMapping
    public List<EvalPlan> getAll() {
        List<EvalPlan> plans = evalPlanRepository.findAll();

        for (EvalPlan plan : plans) {
            if (plan.getActivityIds() != null && !plan.getActivityIds().isEmpty()) {
                plan.setActivities(activityRepository.findAllById(plan.getActivityIds()));
            }
            if (plan.getCommentIds() != null && !plan.getCommentIds().isEmpty()) {
                plan.setComments(commentRepository.findAllById(plan.getCommentIds()));
            }
        }

        return plans;
    }
    // Get one eval plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<EvalPlan> getById(@PathVariable String id) {
        return evalPlanRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new eval plan
    @PostMapping
    public EvalPlan create(@RequestBody EvalPlan evalPlan) {
        return evalPlanRepository.save(evalPlan);
    }

    // Update an existing eval plan
    @PutMapping("/{id}")
    public ResponseEntity<EvalPlan> update(@PathVariable String id, @RequestBody EvalPlan updatedPlan) {
        return evalPlanRepository.findById(id).map(plan -> {
            updatedPlan.setId(id);
            return ResponseEntity.ok(evalPlanRepository.save(updatedPlan));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete an eval plan
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return evalPlanRepository.findById(id).map(plan -> {
            evalPlanRepository.delete(plan);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
