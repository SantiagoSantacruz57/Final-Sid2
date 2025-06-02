package com.example.demo.RestControllers;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserIdController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/get-id")
    public ResponseEntity<Long> getUserId(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        Optional<User> user = userRepository.findByName(name);

        return user.map(value -> ResponseEntity.ok(value.getId()))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
