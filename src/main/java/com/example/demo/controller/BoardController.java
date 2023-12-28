package com.example.demo.controller;

import com.example.demo.model.request.board.BoardDeleteRequest;
import com.example.demo.model.request.board.BoardEditRequest;
import com.example.demo.model.request.board.BoardPostRequest;
import com.example.demo.model.response.BoardListResponse;
import com.example.demo.model.response.BoardResponse;
import com.example.demo.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("boardList")
    public List<BoardListResponse> searchBoardList(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, @RequestParam("direction") Sort.Direction direction) {
        return boardService.searchBoardList(page, pageSize, direction);
    }

    @GetMapping("boardList2")
    public List<BoardListResponse> searchBoardList2(@RequestParam("boardNo") Long boardNo) {
        return boardService.searchBoardList(boardNo);
    }

    @GetMapping("board")
    public BoardResponse searchBoard(@RequestParam("boardNo") Long boardNo) {
        return boardService.getBoard(boardNo);
    }

    @PostMapping("board")
    public BoardResponse writeBoard(@RequestBody BoardPostRequest boardPostRequest) {
        return boardService.writeBoard(boardPostRequest.getTitle(), boardPostRequest.getBody());
    }

    @PutMapping("board")
    public BoardResponse editBoard(@RequestBody BoardEditRequest boardEditRequest) {
        return boardService.editBoard(boardEditRequest.getBoardNo(), boardEditRequest.getBody());
    }

    @DeleteMapping("board")
    public BoardResponse deleteBoard(@RequestBody BoardDeleteRequest boardDeleteRequest) {
        return boardService.deleteBoard(boardDeleteRequest.getBoardNo());
    }
}
