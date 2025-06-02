package com.example.demo.controller;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository; // Asumiendo que tienes un UserRepository para manejar usuarios

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "auth/register";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> processLogin(@RequestParam String username, @RequestParam String password) {

        Optional<User> user = userRepository.findByName(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam Long id,
                                @RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            // You should ideally return with an error attribute to show on the form
            return "register";
        }

        User newUser = new User();
        newUser.setId(id); // only if needed
        newUser.setName(username);
        newUser.setPassword(password);
        userRepository.save(newUser);

        return "redirect:/auth/login";
    }
}