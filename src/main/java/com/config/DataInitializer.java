package com.config;

import com.example.todo_service.model.Board;
import com.example.todo_service.model.Status;
import com.example.todo_service.model.Task;
import com.example.todo_service.repository.BoardRepository;
import com.example.todo_service.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final BoardRepository boardRepository;
    private final TaskRepository taskRepository;

    @PostConstruct
    public void init() {
        if (boardRepository.count() == 0) {
            Board board = Board.builder()
                    .name("Учеба")
                    .build();
            boardRepository.save(board);

            Task task1 = Task.builder()
                    .title("Сделать лабораторную")
                    .description("Канбан + MVC")
                    .status(Status.TODO)
                    .board(board)
                    .build();

            Task task2 = Task.builder()
                    .title("Сдать лабораторную")
                    .description("Показать преподавателю")
                    .status(Status.IN_PROGRESS)
                    .board(board)
                    .build();

            taskRepository.save(task1);
            taskRepository.save(task2);
        }
    }
}
