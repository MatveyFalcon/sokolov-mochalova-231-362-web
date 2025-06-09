package com.example.todo_service.controller;

import com.example.todo_service.model.Board;
import com.example.todo_service.model.Status;
import com.example.todo_service.model.Task;
import com.example.todo_service.service.BoardService;
import com.example.todo_service.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final BoardService boardService;

    // Обработка POST формы добавления задачи
    @PostMapping("/task")
    public String createTask(@RequestParam String title,
            @RequestParam String description,
            @RequestParam Status status,
            @RequestParam Long boardId) {

        Board board = boardService.getBoardById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        Task task = Task.builder()
                .title(title)
                .description(description)
                .status(status)
                .board(board)
                .build();

        taskService.saveTask(task);
        return "redirect:/";
    }


    // Обработка изменения статуса задачи
    @PostMapping("/task/status/{id}")
    public String updateTaskStatus(@PathVariable Long id, @RequestParam Status status) {
        taskService.updateStatus(id, status);
        return "redirect:/";
    }

    @PostMapping("/task/update-status")
    public String updateStatus(@RequestParam Long taskId,
            @RequestParam String direction) {

        Task task = taskService.getTaskById(taskId).orElse(null);
        if (task == null)
            return "redirect:/";

        Status current = task.getStatus();
        Status updated = current;

        if ("left".equals(direction)) {
            if (current == Status.IN_PROGRESS)
                updated = Status.TODO;
            else if (current == Status.DONE)
                updated = Status.IN_PROGRESS;
        } else if ("right".equals(direction)) {
            if (current == Status.TODO)
                updated = Status.IN_PROGRESS;
            else if (current == Status.IN_PROGRESS)
                updated = Status.DONE;
        }

        task.setStatus(updated);
        taskService.saveTask(task);

        return "redirect:/";
    }

    @PostMapping("/task/delete")
    public String deleteTask(@RequestParam Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/";
    }

}
