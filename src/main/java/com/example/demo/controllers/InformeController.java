package com.example.demo.controllers;

import com.example.demo.services.InformeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformeController {

    @Autowired
    private InformeService informeService;

    @GetMapping("/informeUno")
    public String verInformeUno() {
        return "Informes/InformeUno";
    }

    @GetMapping("/informeDos")
    public String verInformeDos() {
        return "Informes/InformeDos";
    }
}
