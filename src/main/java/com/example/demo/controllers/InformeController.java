package com.example.demo.controllers;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.services.InformeService;


@Controller
public class InformeController {

    @Autowired
    private InformeService informeService;

    @GetMapping("/informeUno/{userId}")
    public String verInformeUno(@PathVariable String userId, Model model) {
        List<Map<String, Object>> consolidadoPorMateria = informeService.getConsolidadoNotasPorEstudiante(userId);

        List<Map<String, Object>> consolidadoPlano = new ArrayList<>();

        if (consolidadoPorMateria != null) {
            for (Map<String, Object> materia : consolidadoPorMateria) {
                Object actividadesObj = materia.get("activities");
                if (actividadesObj instanceof List<?>) {
                    List<?> actividades = (List<?>) actividadesObj;

                    // Asegurarnos que cada elemento es Map<String, Object>
                    for (Object actividadObj : actividades) {
                        if (actividadObj instanceof Map<?, ?>) {
                            @SuppressWarnings("unchecked")
                            Map<String, Object> actividad = (Map<String, Object>) actividadObj;
                            consolidadoPlano.add(actividad);
                        }
                    }
                }
            }
        }

        model.addAttribute("consolidado", consolidadoPlano);
        return "Informes/InformeUno";
    }



    @GetMapping("/informeDos/{userId}")
    public String verInformeDos(@PathVariable String userId, Model model) {
        model.addAttribute("notaMinima", informeService.getNotaMinimaRequeridaParaAprobar(userId));
        return "Informes/InformeDos";
    }

}
