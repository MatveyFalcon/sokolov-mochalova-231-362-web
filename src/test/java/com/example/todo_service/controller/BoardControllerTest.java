package com.example.todo_service.controller;

import com.example.todo_service.model.Board;
import com.example.todo_service.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BoardController.class)
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @Test
    void testGetAllBoards() throws Exception {
        when(boardService.getAllBoards()).thenReturn(Collections.singletonList(Board.builder().name("API").build()));
        mockMvc.perform(get("/api/boards"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBoardById() throws Exception {
        when(boardService.getBoardById(1L)).thenReturn(Optional.of(Board.builder().id(1L).name("API").build()));
        mockMvc.perform(get("/api/boards/1"))
                .andExpect(status().isOk());
    }
}
