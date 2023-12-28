package com.example.demo.controller;

import com.example.demo.model.request.comment.CommentDeleteRequest;
import com.example.demo.model.request.comment.CommentEditRequest;
import com.example.demo.model.request.comment.CommentPostRequest;
import com.example.demo.model.response.BoardResponse;
import com.example.demo.model.response.CommentResponse;
import com.example.demo.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("comment/get")
    public CommentResponse get(@RequestParam("commentNo") Long commentNo) {
        return commentService.getComment(commentNo);
    }

    @PostMapping("comment")
    public BoardResponse post(@RequestBody CommentPostRequest commentPostRequest) {
        return commentService.postComment(commentPostRequest.getBoardNo(), commentPostRequest.getCommentBody());
    }

    @PutMapping("comment")
    public String edit(@RequestBody CommentEditRequest commentEditRequest) {
        commentService.editComment(commentEditRequest.getBoardNo(), commentEditRequest.getCommentNo(), commentEditRequest.getCommentBody());
        return "OK";
    }

    @DeleteMapping("comment")
    public String delete(@RequestBody CommentDeleteRequest commentDeleteRequest) {
        commentService.deleteComment(commentDeleteRequest.getBoardNo(), commentDeleteRequest.getCommentNo());
        return "OK";
    }
}
