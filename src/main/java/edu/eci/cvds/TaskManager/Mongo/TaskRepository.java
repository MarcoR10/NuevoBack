package edu.eci.cvds.TaskManager.Mongo;
import java.util.List;

public interface TaskRepository {

    void addTask(Task task);
    void completeTask(String taskId);
    void deleteTask(String taskId);
    List<Task> getAllTasks();
}
