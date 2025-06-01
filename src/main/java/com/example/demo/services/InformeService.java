package com.example.demo.services;

import com.example.demo.mongoModel.Grade;
import com.example.demo.mongoRepository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InformeService {

    @Autowired
    private GradeRepository gradeRepository;

    // Informe 1: Consolidado de notas por estudiante
    public List<Map<String, Object>> getConsolidadoNotasPorEstudiante() {
        List<Map<String, Object>> resultado = new ArrayList<>();
        List<Grade> notas = gradeRepository.findAll();

        // Agrupar por usuario y actividad (porque solo tienes esos campos)
        Map<String, List<Grade>> agrupado = new HashMap<>();

        for (Grade grade : notas) {
            String clave = grade.getUserId();  // Agrupamos solo por estudiante (ya que no hay curso/grupo/semestre)
            agrupado.computeIfAbsent(clave, k -> new ArrayList<>()).add(grade);
        }

        for (Map.Entry<String, List<Grade>> entry : agrupado.entrySet()) {
            String userId = entry.getKey();
            List<Grade> gradesUsuario = entry.getValue();

            double total = 0;
            for (Grade g : gradesUsuario) {
                total += g.getScore(); // Aquí asumimos score ya está ponderado
            }

            double promedio = gradesUsuario.isEmpty() ? 0 : total / gradesUsuario.size();

            Map<String, Object> fila = new HashMap<>();
            fila.put("estudiante", userId);
            fila.put("notaFinal", promedio);

            resultado.add(fila);
        }

        return resultado;
    }

    // Informe 2: Nota mínima requerida (simplificado)
    public List<Map<String, Object>> getNotaMinimaRequeridaParaAprobar() {
        List<Grade> notas = gradeRepository.findAll();
        Map<String, List<Grade>> agrupado = new HashMap<>();

        for (Grade grade : notas) {
            agrupado.computeIfAbsent(grade.getUserId(), k -> new ArrayList<>()).add(grade);
        }

        List<Map<String, Object>> resultado = new ArrayList<>();

        for (Map.Entry<String, List<Grade>> entry : agrupado.entrySet()) {
            String userId = entry.getKey();
            List<Grade> grades = entry.getValue();

            double acumulado = 0;
            int numHechas = 0;

            for (Grade grade : grades) {
                if (grade.getScore() >= 0) { // asumimos 0 o negativo indica no presentado
                    acumulado += grade.getScore();
                    numHechas++;
                }
            }

            int totalActividades = grades.size();
            int restantes = totalActividades - numHechas;

            double notaNecesaria = restantes > 0
                ? (3.0 * totalActividades - acumulado) / restantes
                : 0;

            Map<String, Object> fila = new HashMap<>();
            fila.put("estudiante", userId);
            fila.put("actividadesRestantes", restantes);
            fila.put("notaMinimaNecesaria", Math.max(0, notaNecesaria));

            resultado.add(fila);
        }

        return resultado;
    }


    public List<Map<String, Object>> getConsolidadoNotasPorEstudiante(String id) {
        
        List<Map<String, Object>> resultado = new ArrayList<>();
        



        return resultado;
    }
}
