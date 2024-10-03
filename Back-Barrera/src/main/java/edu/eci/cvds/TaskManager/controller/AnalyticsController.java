package edu.eci.cvds.TaskManager.controller;

import edu.eci.cvds.TaskManager.model.Task;
import edu.eci.cvds.TaskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AnalyticsController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/api/tasks/difficulty")
    public Map<String, Integer> getDifficultyHistogram() {
        List<Task> tasks = taskRepository.findAll();
        Map<String, Integer> difficultyCount = new HashMap<>();

        // Contar las tareas por dificultad
        for (Task task : tasks) {
            String difficulty = task.getDifficult();
            difficultyCount.put(difficulty, difficultyCount.getOrDefault(difficulty, 0) + 1);
        }

        return difficultyCount;
    }
    @GetMapping("/analytics")
    public String showAnalyticsPage() {
        return "analytics"; // Esto buscará un archivo analytics.html en src/main/resources/templates
    }
}
