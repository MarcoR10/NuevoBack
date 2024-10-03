package edu.eci.cvds.TaskManager.service;


import edu.eci.cvds.TaskManager.model.Task;
import edu.eci.cvds.TaskManager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

@SpringBootTest
public class TestService {
    @Mock
    private TaskService taskService;
    @InjectMocks
    private TaskRepository taskRepository;

    private Task task;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
        // Configura una tarea registrada
        task = new Task("Task 1");
        task.setId("1"); // Simula que MongoDB ha generado un ID
    }

    @Test
    public void testGetRegisteredTask_Success() {
        // Simula el comportamiento del repositorio
        when(taskRepository.findById("1")).thenReturn(Optional.of(task));

        // Llama al método que estamos probando
        Task result = taskService.geTaskByIdTask("1");

        // Verifica que la consulta fue exitosa
        assertNotNull(result); // Verifica que no sea nulo
        assertEquals("1", result.getId()); // Verifica que el ID sea el esperado
    }

    @Test
    void testGetTask_NoTasks() {
        Mockito.when(taskRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Task result = taskService.getTaskByDescription("2");
        assertNull(result);
    }
}
