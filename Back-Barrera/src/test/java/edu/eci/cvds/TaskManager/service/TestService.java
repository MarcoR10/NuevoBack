package edu.eci.cvds.TaskManager.service;


import edu.eci.cvds.TaskManager.model.Task;
import edu.eci.cvds.TaskManager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
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

    private Task task;

    // Dado que no hay ninguna tarea registrada, Cuándo la consulto a nivel de servicio, Entonces la consulta no retornará ningún resultado.
    @Test
    public void testGetTask_NoTasks() {
        // Consultar una tarea por un ID que no existe
        Optional<Task> result = taskService.geTaskByIdTask("1");

        // Verificar que no se encontró ninguna tarea
        assertFalse(result.isPresent(), "Se esperaba que no hubiera ninguna tarea registrada.");
    }
}
