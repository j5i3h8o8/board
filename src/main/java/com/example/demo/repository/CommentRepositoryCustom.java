package com.example.demo.repository;

import com.example.demo.model.CommentStatus;
import com.example.demo.model.entity.Comment;
import com.example.demo.model.entity.QBoard;
import com.example.demo.model.entity.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public Comment find(Long commentId) {
        return queryFactory.select(QComment.comment).from(QComment.comment).where(QComment.comment.commentNo.eq(commentId)).fetchOne();
    }

    public Comment find(Long commentId, Long boardId, CommentStatus commentStatus) {
        return queryFactory.select(QComment.comment).from(QComment.comment).where(QComment.comment.commentNo.eq(commentId)).where(QComment.comment.commentStatus.eq(commentStatus)).where(QBoard.board.boardNo.eq(boardId)).fetchOne();
    }
}
