package edu.eci.cvds.TaskManager.service;


import edu.eci.cvds.TaskManager.model.Task;
import edu.eci.cvds.TaskManager.repository.TaskRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@SpringBootTest
public class TestService {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;


    // Dado que no hay ninguna tarea registrada, Cuándo la consulto a nivel de servicio, Entonces la consulta no retornará ningún resultado.
    @Test
    public void testGetTask_NotExist() {
        // Consultar una tarea por un ID que no existe
        Optional<Task> result = taskService.geTaskByIdTask("1");

        // Verificar que no se encontró ninguna tarea
        assertFalse(result.isPresent(), "Se esperaba que no hubiera ninguna tarea registrada.");
    }

    //Dado que no hay ninguna tarea registrada, Cuándo lo creo a nivel de servicio, Entonces la creación será exitosa.

    @Test
    public void testCreateTask_Nice() {
    // Dado que no hay ninguna tarea registrada
    long initialCount = taskRepository.count();

    // Cuándo lo creo a nivel de servicio
    Task newTask = taskService.addTask("Nueva Tarea");

    // Entonces la creación será exitosa
    assertNotNull(newTask.getId());
    assertEquals("Nueva Tarea", newTask.getDescription());

    // Validar que el número de tareas aumentó
    long finalCount = taskRepository.count();
    assertEquals(initialCount + 1, finalCount);
}

    // Dado que tengo 1 tarea registrada, Cuándo la elimino a nivel de servicio, Entonces la eliminación será exitosa.
    @Test
    public void testDeleteTask_Nice() {
    // Dado que tengo 1 tarea registrada
    Task task = taskService.addTask("Tarea a eliminar");

    // Cuándo la elimino a nivel de servicio
    boolean isDeleted = taskService.deleteTask(task.getId());

    // Entonces la eliminación será exitosa
    assertTrue(isDeleted);
    assertFalse(taskRepository.existsById(task.getId()));
}

    //Dado que tengo 1 tarea registrada, Cuando lo consulto a nivel de servicio, 
    //Entonces la consulta será exitosa validando el campo id.

    @Test
    public void testConsultTaskById_Nice() {
    // Dado que tengo 1 tarea registrada
    Task task = taskService.addTask("Tarea registrada");

    // Cuándo la consulto a nivel de servicio
    Task foundTask = taskService.queryTaskById(task.getId());

    // Entonces la consulta será exitosa validando el campo id
    assertNotNull(foundTask);
    assertEquals(task.getId(), foundTask.getId());
}

//Dado que tengo 1 tarea registrada, Cuándo la elimino y consulto a nivel de servicio, 
//Entonces el resultado de la consulta no retornará ningún resultado.
@Test
public void testDeleteAndConsult() {
    // Dado que tengo una tarea registrada
    Task task = taskService.addTask("Esta Tarea se eliminara");

    // Primero la elimino
    boolean isDeleted = taskService.deleteTask(task.getId());

    // Luego intento consultarla y verifico que no exista (usando Optional para manejar la ausencia)
    Optional<Task> foundTask = taskService.geTaskByIdTask(task.getId());

    // Verifico que la tarea no esté presente
    assertTrue(foundTask.isEmpty());
    

    // Verifico que la eliminación fue exitosa
    assertTrue(isDeleted);
}
    
}
