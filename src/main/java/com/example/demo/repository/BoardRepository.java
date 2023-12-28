package com.example.demo.repository;

import com.example.demo.model.BoardStatus;
import com.example.demo.model.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByBoardStatus(BoardStatus boardStatus, Pageable pageable);

    List<Board> findTop10ByBoardStatusAndBoardNoLessThanOrderByBoardNoDesc(BoardStatus boardStatus, Long id);

    List<Board> findTop10ByBoardStatusOrderByBoardNoDesc(BoardStatus boardStatus);
}
