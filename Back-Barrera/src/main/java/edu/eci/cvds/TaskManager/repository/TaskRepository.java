package edu.eci.cvds.TaskManager.repository;

import edu.eci.cvds.TaskManager.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    // Buscar una tarea por descripción
    @Query("{description:'?0'}")
    Task findTaskByDescription(String description);

    // Buscar todas las tareas completadas o no completadas
    @Query(value="{completed: ?0}", fields="{'description' : 1, 'completed' : 1}")
    List<Task> findAllByCompletionStatus(boolean completed);

    // Contar el número de tareas completadas
    @Query(value="{completed: true}")
    long countCompletedTasks();
}



