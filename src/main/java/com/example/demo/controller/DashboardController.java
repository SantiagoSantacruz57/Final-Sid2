package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.services.UserSubjectService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserSubjectService userSubjectService;

    @GetMapping
    public String showDashboard(Model model) {
        // TODO: Obtener el usuario actual de la sesión
        // Por ahora usamos un usuario de ejemplo
        User currentUser = new User(1L, "Usuario Actual", "password");
        
        List<Subject> userSubjects = userSubjectService.getSubjectsByUser(currentUser);
        
        model.addAttribute("username", currentUser.getName());
        model.addAttribute("subjects", userSubjects);
        model.addAttribute("hasSubjects", !userSubjects.isEmpty());
        
        return "dashboard/index";
    }
} 