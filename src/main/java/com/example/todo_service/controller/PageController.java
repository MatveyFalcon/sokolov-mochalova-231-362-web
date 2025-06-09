package com.example.todo_service.controller;

import com.example.todo_service.model.Board;
import com.example.todo_service.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model) {
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "index"; // index.html
    }
}
