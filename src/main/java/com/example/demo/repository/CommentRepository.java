package com.example.demo.repository;

import com.example.demo.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentNoAndBoard_BoardNo(Long commentNo, Long boardNo);
}
