package edu.eci.cvds.TaskManager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.TaskManager.repository.TaskRepository;
import edu.eci.cvds.TaskManager.service.TaskService;

//Prueba 2

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    public void testQuerySingleTask() {
        Task task = new Task();
        task.setId("1");
        task.setDescription("Test Task");

        when(taskRepository.findById("1")).thenReturn(Optional.of(task));

        Task queriedTask = taskService.queryTaskById("1");
        assertNotNull(queriedTask);
        assertEquals(task.getId(), queriedTask.getId());
    }

    @Test
    public void testCreateTask() {
        Task task = new Task("New Task"); // Debes proporcionar una descripción
        taskService.addTask(task.getDescription()); // Pasa la descripción al método
        // Realiza las aserciones necesarias
    }


    @Test
    public void testDeleteTask() {
        Task task = new Task("Task to Delete");
        task.setId("1"); 

        when(taskRepository.findById("1")).thenReturn(Optional.of(task));

        boolean isDeleted = taskService.deleteTask("1");
        assertTrue(isDeleted);
        verify(taskRepository, times(1)).deleteById("1");
    }

    @Test
    public void testDeleteAndQueryTask() {
        Task task = new Task("Task to Delete");
        task.setId("1");
        when(taskRepository.findById("1")).thenReturn(Optional.of(task));
        taskService.addTask(task.getDescription());
        boolean isDeleted = taskService.deleteTask(task.getId());
        assertTrue(isDeleted);
    }

    @Test
    public void testGetTask_NoTasks() {
        List<Task> tasks = taskService.getTasksByCompletionStatus(false);
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void testGetTask_Success() {
        Task task = new Task("Test Task");
        task.setId("1"); // Asigna un ID a la tarea

        when(taskRepository.findTaskByDescription("Test Task")).thenReturn(task); // Mockea el repositorio

        Task queriedTask = taskService.getTaskByDescription("Test Task");
        assertNotNull(queriedTask);
        assertEquals(task.getId(), queriedTask.getId());
    }
}
