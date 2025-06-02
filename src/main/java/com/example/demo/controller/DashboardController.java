package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserSubjectService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserSubjectService userSubjectService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showDashboard(@RequestParam String name, Model model) {
    Optional<User> optionalUser = userRepository.findByName(name);

        if (optionalUser.isEmpty()) {
            return "redirect:/auth/login"; // Or show an error page
        }

        User currentUser = optionalUser.get();

        List<Subject> userSubjects = userSubjectService.getSubjectsByUser(currentUser);

        model.addAttribute("username", currentUser.getName());
        model.addAttribute("subjects", userSubjects);
        model.addAttribute("hasSubjects", !userSubjects.isEmpty());

        return "dashboard/index";
    }



} 