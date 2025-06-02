package com.example.demo.services;

import com.example.demo.mongoModel.Activity;
import com.example.demo.mongoModel.ActivityType;
import com.example.demo.mongoModel.Grade;
import com.example.demo.mongoRepository.ActivityRepository;
import com.example.demo.mongoRepository.ActivityTypeRepository;
import com.example.demo.mongoRepository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InformeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityTypeRepository activityTypeRepository;

    // Informe 1: Consolidado de notas por estudiante (general)
    public List<Map<String, Object>> getConsolidadoNotasPorEstudiante() {
        List<Map<String, Object>> resultado = new ArrayList<>();
        List<Grade> notas = gradeRepository.findAll();

        Map<String, List<Grade>> agrupado = new HashMap<>();

        for (Grade grade : notas) {
            String clave = grade.getUserId();
            agrupado.computeIfAbsent(clave, k -> new ArrayList<>()).add(grade);
        }

        for (Map.Entry<String, List<Grade>> entry : agrupado.entrySet()) {
            String userId = entry.getKey();
            List<Grade> gradesUsuario = entry.getValue();

            double total = 0;
            for (Grade g : gradesUsuario) {
                total += g.getScore();
            }

            double promedio = gradesUsuario.isEmpty() ? 0 : total / gradesUsuario.size();

            Map<String, Object> fila = new HashMap<>();
            fila.put("estudiante", userId);
            fila.put("notaFinal", promedio);

            resultado.add(fila);
        }

        return resultado;
    }

    // Informe 2: Nota mínima requerida (general)
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
                if (grade.getScore() >= 0) {
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

    // Informe 1: Consolidado de notas por estudiante (individual)
    public List<Map<String, Object>> getConsolidadoNotasPorEstudiante(String userId) {
        List<Grade> grades = gradeRepository.findByUserId(userId);
        List<Map<String, Object>> result = new ArrayList<>();

        Map<String, List<Map<String, Object>>> subjectMap = new HashMap<>();

        for (Grade grade : grades) {
            Activity activity = activityRepository.findById(grade.getActivityId()).orElse(null);
            if (activity == null) continue;

            ActivityType activityType = activityTypeRepository.findById(activity.getTypeId()).orElse(null);

            Map<String, Object> activityData = new HashMap<>();
            activityData.put("activityId", activity.getId());
            activityData.put("name", activity.getName());
            activityData.put("description", activity.getDescription());
            activityData.put("type", activityType != null ? activityType.getName() : "Desconocido");
            activityData.put("score", grade.getScore());

            String subjectId = activity.getSubjectId();
            if (subjectId == null) continue;

            subjectMap.computeIfAbsent(subjectId, k -> new ArrayList<>()).add(activityData);
        }

        for (Map.Entry<String, List<Map<String, Object>>> entry : subjectMap.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isEmpty()) continue;

            Map<String, Object> subjectData = new HashMap<>();
            subjectData.put("subjectId", entry.getKey());
            subjectData.put("activities", entry.getValue());
            result.add(subjectData);
        }

        return result;
    }

    // Informe 2: Nota mínima requerida (individual)
    public List<Map<String, Object>> getNotaMinimaRequeridaParaAprobar(String userId) {
        List<Grade> grades = gradeRepository.findByUserId(userId);
        List<Map<String, Object>> resultado = new ArrayList<>();

        double acumulado = 0;
        int numHechas = 0;

        for (Grade grade : grades) {
            if (grade.getScore() >= 0) {
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
        return resultado;
    }
}
