package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.services.SubjectService;
import com.example.demo.services.UserSubjectService;

@Controller("subjectMvcController")
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserSubjectService userSubjectService;

    @GetMapping("/available")
    public String showAvailableSubjects(Model model) {
        // TODO: Obtener el usuario actual de la sesión
        // Por ahora usamos un usuario de ejemplo
        User currentUser = new User(1L, "Usuario Actual", "password");
        
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("currentUser", currentUser);
        return "subjects/available";
    }

    @PostMapping("/enroll")
    public String enrollInSubject(@RequestParam("subjectCode") String subjectCode, 
                                @RequestParam("userId") Long userId,
                                RedirectAttributes redirectAttributes) {
        try {
            // TODO: Obtener el usuario actual de la sesión
            User currentUser = new User(userId, "Usuario Actual", "password");
            Subject subject = subjectService.getSubjectByCode(subjectCode);
            
            userSubjectService.enrollUserInSubject(currentUser, subject);
            redirectAttributes.addFlashAttribute("success", "Te has inscrito exitosamente en la materia");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo inscribir en la materia: " + e.getMessage());
        }
        
        return "redirect:/subjects/available";
    }
} 