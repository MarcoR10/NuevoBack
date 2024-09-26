package edu.eci.cvds.TaskManager.controller;

import edu.eci.cvds.TaskManager.model.Task;
import edu.eci.cvds.TaskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Obtener todas las tareas
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getTasksByCompletionStatus(false);
        return ResponseEntity.ok(tasks);
    }

    // Obtener tareas por estado de completado (true o false)
    @GetMapping("/completed/{status}")
    public ResponseEntity<List<Task>> getTasksByCompletionStatus(@PathVariable boolean status) {
        List<Task> tasks = taskService.getTasksByCompletionStatus(status);
        return ResponseEntity.ok(tasks);
    }

    // Contar cuántas tareas están completadas
    @GetMapping("/completed/count")
    public ResponseEntity<Long> countCompletedTasks() {
        long count = taskService.countCompletedTasks();
        return ResponseEntity.ok(count);
    }

    // Añadir una nueva tarea
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody String description) {
        Task task = taskService.addTask(description);
        return ResponseEntity.ok(task);
    }

    // Eliminar una tarea por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // Completar una tarea
    @PutMapping("/complete/{id}")
    public ResponseEntity<Task> completeTask(@PathVariable String id) {
        Task task = taskService.completeTask(id);
        return ResponseEntity.ok(task);
    }

    // Buscar una tarea por descripción
    @GetMapping("/description/{description}")
    public ResponseEntity<Task> getTaskByDescription(@PathVariable String description) {
        Task task = taskService.getTaskByDescription(description);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
