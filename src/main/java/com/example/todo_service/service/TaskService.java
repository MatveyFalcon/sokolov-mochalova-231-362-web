package com.example.todo_service.service;

import com.example.todo_service.model.Task;
import com.example.todo_service.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.todo_service.model.Status;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    public void updateStatus(Long id, Status newStatus) {
    Task task = getTaskById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    task.setStatus(newStatus);
    taskRepository.save(task);
    }
}
