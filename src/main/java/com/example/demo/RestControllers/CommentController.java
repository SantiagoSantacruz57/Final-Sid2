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
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EvalPlanRepository evalPlanRepository;

    // ===== OPERACIONES BÁSICAS CRUD =====

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

    // Create a new comment (standalone)
    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    // Update comment
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable String id, @RequestBody Comment updatedComment) {
        return commentRepository.findById(id).map(comment -> {
            comment.setUser(updatedComment.getUser());
            comment.setContent(updatedComment.getContent());
            return ResponseEntity.ok(commentRepository.save(comment));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete comment
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id) {
        return commentRepository.findById(id).map(comment -> {
            // También remover de planes de evaluación que lo referencien
            removeCommentFromAllPlans(id);
            commentRepository.delete(comment);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // ===== OPERACIONES ESPECÍFICAS PARA PLANES DE EVALUACIÓN =====

    // Add comment to evaluation plan - FUNCIONALIDAD PRINCIPAL
    @PostMapping("/evalplan/{planId}")
    public ResponseEntity<Comment> addCommentToEvalPlan(
            @PathVariable String planId, 
            @RequestBody Comment comment) {
        
        Optional<EvalPlan> planOptional = evalPlanRepository.findById(planId);
        if (planOptional.isPresent()) {
            EvalPlan plan = planOptional.get();
            
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
        }
        
        return ResponseEntity.notFound().build();
    }

    // Get comments for a specific evaluation plan
    @GetMapping("/evalplan/{planId}")
    public ResponseEntity<List<Comment>> getCommentsForEvalPlan(@PathVariable String planId) {
        Optional<EvalPlan> planOptional = evalPlanRepository.findById(planId);
        if (planOptional.isPresent()) {
            EvalPlan plan = planOptional.get();
            if (plan.getCommentIds() != null && !plan.getCommentIds().isEmpty()) {
                List<Comment> comments = (List<Comment>) commentRepository.findAllById(plan.getCommentIds());
                return ResponseEntity.ok(comments);
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        return ResponseEntity.notFound().build();
    }

    // Remove comment from specific evaluation plan
    @DeleteMapping("/evalplan/{planId}/comment/{commentId}")
    public ResponseEntity<?> removeCommentFromPlan(
            @PathVariable String planId, 
            @PathVariable String commentId) {
        
        Optional<EvalPlan> planOptional = evalPlanRepository.findById(planId);
        if (planOptional.isPresent()) {
            EvalPlan plan = planOptional.get();
            if (plan.getCommentIds() != null) {
                plan.getCommentIds().remove(commentId);
                evalPlanRepository.save(plan);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    // ===== MÉTODOS AUXILIARES =====

    // Count comments for a plan
    @GetMapping("/evalplan/{planId}/count")
    public ResponseEntity<Integer> getCommentCountForPlan(@PathVariable String planId) {
        Optional<EvalPlan> planOptional = evalPlanRepository.findById(planId);
        if (planOptional.isPresent()) {
            EvalPlan plan = planOptional.get();
            int count = (plan.getCommentIds() != null) ? plan.getCommentIds().size() : 0;
            return ResponseEntity.ok(count);
        }
        return ResponseEntity.notFound().build();
    }

    // Get all comments by user
    @GetMapping("/user/{userName}")
    public List<Comment> getCommentsByUser(@PathVariable String userName) {
        return commentRepository.findAll().stream()
                .filter(comment -> userName.equals(comment.getUser()))
                .toList();
    }

    // Search comments by content
    @GetMapping("/search")
    public List<Comment> searchComments(@RequestParam String query) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getContent().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }

    // ===== MÉTODOS PRIVADOS =====

    // Remove comment reference from all evaluation plans
    private void removeCommentFromAllPlans(String commentId) {
        List<EvalPlan> allPlans = evalPlanRepository.findAll();
        for (EvalPlan plan : allPlans) {
            if (plan.getCommentIds() != null && plan.getCommentIds().contains(commentId)) {
                plan.getCommentIds().remove(commentId);
                evalPlanRepository.save(plan);
            }
        }
    }
} 