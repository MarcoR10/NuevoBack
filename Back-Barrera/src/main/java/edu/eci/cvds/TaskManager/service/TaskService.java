package edu.eci.cvds.TaskManager.service;

import edu.eci.cvds.TaskManager.model.Task;
import edu.eci.cvds.TaskManager.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task geTaskByIdTask(String id){
        return taskRepository.findTaskById(id).orElseThrow(() -> new RuntimeException("Task not found"));

    }
    public Task getTaskByDescription(String description) {
        Task task = taskRepository.findTaskByDescription(description);
        if (task == null) {
            throw new RuntimeException("Task not found");
        }
        return task;
    }

    public List<Task> getTasksByCompletionStatus(boolean completed) {
        return taskRepository.findAllByCompletionStatus(completed);
    }

    public long countCompletedTasks() {
        return taskRepository.countCompletedTasks();
    }

    public Task addTask(String description) {
        Task task = new Task(description);
        taskRepository.save(task);
        return task;
    }

    public boolean deleteTask(String taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return true;
        } 
        return false;
    }

    public Task completeTask(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(true);
        return taskRepository.save(task);
    }

    public Task queryTaskById(String id) {
        return taskRepository.findById(id).orElseThrow(null);
    }

    public List<Task> queryAllTasks() {
        return taskRepository.findAll(); // O lo que devuelva tu repositorio
    }    
}
