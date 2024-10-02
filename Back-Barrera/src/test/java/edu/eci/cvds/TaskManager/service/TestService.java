package edu.eci.cvds.TaskManager.service;


import edu.eci.cvds.TaskManager.model.Task;
import edu.eci.cvds.TaskManager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@SpringBootTest
public class TestService {

    private TaskService taskService;
    private TaskRepository taskRepository;

    @BeforeEach

    void setUp() {
    taskRepository = Mockito.mock(TaskRepository.class);
    taskService = new TaskService(taskRepository);
    }

    @Test
    public void testGetTask_Success() {
        Task task = new Task("Task 1");
        task.getId();
        Mockito.when(taskRepository.findById("1")).thenReturn(Optional.of(task));

        Task result = taskService.getTaskByDescription("1");
        assertNotNull(result);
        assertEquals("1", result.getId());
    }

    @Test
    void testGetTask_NoTasks() {
        Mockito.when(taskRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        Task result = taskService.getTaskByDescription("2");
        assertNull(result);
    }
}
