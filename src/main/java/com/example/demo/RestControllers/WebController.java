package com.example.demo.RestControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    // Landing page de Trackademic
    @GetMapping("/")
    public String home() {
        return "forward:/home.html";
    }
    
    // Sistema de planes de evaluación y comentarios
    @GetMapping("/planes-evaluacion")
    public String planesEvaluacion() {
        return "forward:/planes-evaluacion.html";
    }
    
    // Alias para comentarios (redirige a planes)
    @GetMapping("/comentarios")
    public String comentarios() {
        return "redirect:/planes-evaluacion";
    }
    

    // Gestión de notas (para futuras funcionalidades) 
    @GetMapping("/mis-notas")
    public String misNotas() {
        return "forward:/notas.html";
    }
} 