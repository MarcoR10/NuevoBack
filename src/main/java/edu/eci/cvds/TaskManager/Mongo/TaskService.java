package edu.eci.cvds.TaskManager.Mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
>>>>>>> 478ff94b5c4b76b095b6f3fe5026c984ed289367

@Service
@RequestMapping("/api/tasks")
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @PostMapping
    public void addTask(Task task) {
        taskRepository.addTask(task);
    }
    @PutMapping
    public void completeTask(String taskId) {
        taskRepository.completeTask(taskId);
    }
    @DeleteMapping
    public void deleteTask(String taskId) {
        taskRepository.deleteTask(taskId);
    }

<<<<<<< HEAD
    @GetMapping
=======
>>>>>>> 478ff94b5c4b76b095b6f3fe5026c984ed289367
    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }
}

