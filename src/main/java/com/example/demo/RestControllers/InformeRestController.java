package com.example.demo.RestControllers;

import com.example.demo.services.InformeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/informes")
public class InformeRestController {

    @Autowired
    private InformeService informeService;

    // Informe 1: Consolidado de notas
    @GetMapping("/consolidado")
    public List<Map<String, Object>> obtenerConsolidadoNotas() {
        return informeService.getConsolidadoNotasPorEstudiante();
    }

    // Informe 2: Nota mínima requerida
    @GetMapping("/nota-minima")
    public List<Map<String, Object>> obtenerNotaMinimaRequerida() {
        return informeService.getNotaMinimaRequeridaParaAprobar();
    }
}
