package com.example.demo.repository;

import com.example.demo.model.BoardStatus;
import com.example.demo.model.entity.Board;
import com.example.demo.model.entity.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustom {
    private final static QBoard board = QBoard.board;
    private final JPAQueryFactory queryFactory;

    public Optional<Board> find(Long boardNo) {
        return Optional.ofNullable(queryFactory.select(board).from(board).leftJoin(board.comments).where(board.boardNo.eq(boardNo)).where(board.boardStatus.eq(BoardStatus.ACTIVE)).fetchOne());
    }
}
