package edu.eci.cvds.TaskManager.Mongo;

import org.springframework.data.annotation.Id;

@Document(collection = "tasks")
public class Task {

    @Id
    private String id;
    private String description;
    private boolean completed;

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
