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

    // Get eval plans with filters
    @GetMapping("/filter")
    public List<EvalPlan> getFilteredEvalPlans(
            @RequestParam(required = false) String subjectId,
            @RequestParam(required = false) Integer minLikes,
            @RequestParam(required = false) String createdAt // ISO-8601 string, e.g. "2024-06-01"
    ) {
        List<EvalPlan> plans = evalPlanRepository.findAll();
        return plans.stream()
                .filter(plan -> subjectId == null || subjectId.equals(plan.getSubjectId()))
                .filter(plan -> minLikes == null || (plan.getLikes() != null && plan.getLikes().size() >= minLikes))
                .filter(plan -> {
                    if (createdAt == null) return true;
                    if (plan.getCreatedAt() == null) return false;
                    // Compare only the date part (yyyy-MM-dd)
                    String planDate = plan.getCreatedAt().length() >= 10 ? plan.getCreatedAt().substring(0, 10) : plan.getCreatedAt();
                    return planDate.equals(createdAt);
                })
                .toList();
    }
}
