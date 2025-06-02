package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/login/{username}/{password}")
    public String processLogin(@PathVariable String username, @PathVariable String password) {

        Optional<User> user = userRepository.findByName(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            // Lógica de inicio de sesión exitosa
            return "redirect:/dashboard";
        }
        // Lógica de inicio de sesión fallido
        return "redirect:/auth/login";
    }

    @PostMapping("/register")
    public String processRegister() {
        // TODO: Implementar lógica de registro
        return "redirect:/auth/login";
    }
} 