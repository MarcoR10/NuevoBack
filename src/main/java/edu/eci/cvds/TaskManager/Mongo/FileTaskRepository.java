package edu.eci.cvds.TaskManager.Mongo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class FileTaskRepository implements TaskRepository {

    private final String filePath = "tasks.txt";

    @Override
    public void addTask(Task task) { // Implementando correctamente el método addTask
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(task.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completeTask(String taskId) {
         // Leer todas las tareas del archivo
         List<String> tasks = new ArrayList<>();
         try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
             String line;
             while ((line = reader.readLine()) != null) {
                 String[] taskData = line.split(",");
                 if (taskData[0].equals(taskId)) {
                     // Marcar la tarea como completada cambiando el valor de "completed" a "true"
                     taskData[2] = "true";
                 }
                 tasks.add(String.join(",", taskData)); // Reconstruir la línea y añadirla a la lista
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
 
         // Escribir de nuevo todas las tareas en el archivo
         try (FileWriter writer = new FileWriter(filePath, false)) { // Sobrescribir el archivo
             for (String task : tasks) {
                 writer.write(task + "\n");
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    @Override
    public void deleteTask(String taskId) {
        // Leer todas las tareas del archivo
        List<String> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskData = line.split(",");
                if (!taskData[0].equals(taskId)) {
                    // Solo agregar a la lista las tareas que no tengan el ID a eliminar
                    tasks.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Escribir de nuevo todas las tareas en el archivo (excepto la eliminada)
        try (FileWriter writer = new FileWriter(filePath, false)) { // Sobrescribir el archivo
            for (String task : tasks) {
                writer.write(task + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getAllTasks() { // Implementando correctamente el método getAllTasks
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(parseTask(line)); // parseTask() convierte el String en una instancia de Task
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private Task parseTask(String line) {
        // Convierte una línea del archivo en un objeto Task
        return new Task(); // Proporciona una implementación real de esto
    
    }

}

