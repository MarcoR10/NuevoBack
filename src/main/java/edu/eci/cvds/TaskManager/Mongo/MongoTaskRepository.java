package edu.eci.cvds.TaskManager.Mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MongoTaskRepository implements TaskRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addTask(Task task) {
        mongoTemplate.save(task); // Guarda el objeto Task en colección
    }

    @Override
    public void completeTask(String taskId) {
        // Buscar la tarea por ID
        Task task = mongoTemplate.findById(taskId, Task.class);
        if (task != null) {
            task.setCompleted(true); // Marcar la tarea como completada
            mongoTemplate.save(task); // Actualizar la tarea en la base de datos
        } else {
            throw new RuntimeException("Task not found with ID: " + taskId);
        }
    }

    @Override
    public void deleteTask(String taskId) {
        Task task = mongoTemplate.findById(taskId, Task.class);
        if (task != null) {
            mongoTemplate.remove(task); // Eliminar la tarea de la colección
        } else {
            throw new RuntimeException("Task not found with ID: " + taskId);
        }
    }

    @Override
    public List<Task> getAllTasks() {
        return mongoTemplate.findAll(Task.class); // Devuelve todas Tareas
    }
}

