package com.example.demo.model.entity;

import com.example.demo.model.CommentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long commentNo;

    @Getter
    @Setter
    private String body;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public boolean isActive() {
        return commentStatus == CommentStatus.ACTIVE;
    }
}
