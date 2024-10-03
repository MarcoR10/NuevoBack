
package edu.eci.cvds.TaskManager.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskTest {

    @Test
    public void testConstructorWithDescription() {
        Task task = new Task("Test description");

        assertNotNull(task.getDescription(), "La descripción no debe ser nula");
        assertEquals("Test description", task.getDescription(), "La descripción no coincide");
        assertFalse(task.isCompleted(), "El estado de completado debería ser falso");
    }

    @Test
    public void testConstructorWithoutDescription() {
        Task task = new Task();

        assertNull(task.getDescription(), "La descripción debería ser nula");
        assertFalse(task.isCompleted(), "El estado de completado debería ser falso");
    }

    @Test
    public void testSettersAndGetters() {
        Task task = new Task();

        task.setDescription("New description");
        task.setCompleted(true);

        assertEquals("New description", task.getDescription(), "La descripción no coincide");
        assertTrue(task.isCompleted(), "El estado de completado debería ser verdadero");
    }

    @Test
    public void testSetCompleted() {
        Task task = new Task("Sample task");

        task.setCompleted(true);
        assertTrue(task.isCompleted(), "El estado de completado debería ser verdadero");

        task.setCompleted(false);
        assertFalse(task.isCompleted(), "El estado de completado debería ser falso");
    }

    @Test
    public void testSetDescription() {
        Task task = new Task();
        task.setDescription("Test task description");

        assertEquals("Test task description", task.getDescription(), "La descripción no coincide");
    }
}
