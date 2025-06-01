package com.example.demo.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.mongoModel.Comment;
import com.example.demo.mongoModel.EvalPlan;
import com.example.demo.mongoRepository.CommentRepository;
import com.example.demo.mongoRepository.EvalPlanRepository;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EvalPlanRepository evalPlanRepository;

    // Get all comments
    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Get comment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        return commentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new comment
    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    // Add comment to evaluation plan
    @PostMapping("/evalplan/{planId}")
    public ResponseEntity<Comment> addCommentToEvalPlan(
            @PathVariable String planId, 
            @RequestBody Comment comment) {
        
        return evalPlanRepository.findById(planId).map(plan -> {
            // Save the comment first
            Comment savedComment = commentRepository.save(comment);
            
            // Add comment ID to the plan
            if (plan.getCommentIds() == null) {
                plan.setCommentIds(new ArrayList<>());
            }
            plan.getCommentIds().add(savedComment.getId());
            
            // Save the updated plan
            evalPlanRepository.save(plan);
            
            return ResponseEntity.ok(savedComment);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Get comments for a specific evaluation plan
    @GetMapping("/evalplan/{planId}")
    public ResponseEntity<List<Comment>> getCommentsForEvalPlan(@PathVariable String planId) {
        var planOptional = evalPlanRepository.findById(planId);
        if (planOptional.isPresent()) {
            EvalPlan plan = planOptional.get();
            if (plan.getCommentIds() != null && !plan.getCommentIds().isEmpty()) {
                List<Comment> comments = (List<Comment>) commentRepository.findAllById(plan.getCommentIds());
                return ResponseEntity.ok(comments);
            }
            return ResponseEntity.ok(new ArrayList<Comment>());
        }
        return ResponseEntity.notFound().build();
    }

    // Update comment
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable String id, @RequestBody Comment updatedComment) {
        return commentRepository.findById(id).map(comment -> {
            updatedComment.setId(id);
            return ResponseEntity.ok(commentRepository.save(updatedComment));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete comment
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id) {
        return commentRepository.findById(id).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
} 