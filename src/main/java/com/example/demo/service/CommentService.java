package com.example.demo.service;

import com.example.demo.model.CommentStatus;
import com.example.demo.model.entity.Comment;
import com.example.demo.model.response.BoardResponse;
import com.example.demo.model.response.CommentResponse;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.BoardRepositoryCustom;
import com.example.demo.repository.CommentRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class CommentService {
    private final BoardRepository boardRepository;
    private final BoardRepositoryCustom boardRepositoryCustom;
    private final CommentRepositoryCustom commentRepositoryCustom;

    public BoardResponse postComment(Long boardId, String commentBody) {
        return boardRepositoryCustom.find(boardId).map(board -> board.addComment(commentBody)).map(boardRepository::save).map(BoardResponse::from).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
    }

    public void editComment(Long boardId, Long commentId, String commentBody) {
        Comment comment = commentRepositoryCustom.find(commentId, boardId, CommentStatus.ACTIVE);
        comment.setBody(commentBody);
    }

    public CommentResponse getComment(Long commentNo) {
        return CommentResponse.from(commentRepositoryCustom.find(commentNo));
    }

    public void deleteComment(Long boardId, Long commentId) {
        Comment comment = commentRepositoryCustom.find(commentId, boardId, CommentStatus.ACTIVE);
        comment.setCommentStatus(CommentStatus.DELETE);
    }
}
