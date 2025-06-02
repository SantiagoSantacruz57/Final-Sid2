package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "auth/register";
    }

    @PostMapping("/login")
    public String processLogin() {
        // TODO: Implementar lógica de login
        return "redirect:/dashboard";
    }

    @PostMapping("/register")
    public String processRegister() {
        // TODO: Implementar lógica de registro
        return "redirect:/auth/login";
    }
} 