package com.example.todo_service.service;

import com.example.todo_service.model.Board;
import com.example.todo_service.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardServiceTest {

    private BoardRepository boardRepository;
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        boardRepository = mock(BoardRepository.class);
        boardService = new BoardService(boardRepository);
    }

    @Test
    void testGetAllBoards() {
        when(boardRepository.findAll()).thenReturn(Arrays.asList(new Board(), new Board()));
        assertEquals(2, boardService.getAllBoards().size());
    }

    @Test
    void testGetBoardById() {
        Board board = Board.builder().id(1L).name("Test").build();
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));
        Optional<Board> found = boardService.getBoardById(1L);
        assertTrue(found.isPresent());
        assertEquals("Test", found.get().getName());
    }

    @Test
    void testCreateBoard() {
        Board board = Board.builder().name("New").build();
        when(boardRepository.save(board)).thenReturn(board);
        assertNotNull(boardService.createBoard(board));
    }
}
