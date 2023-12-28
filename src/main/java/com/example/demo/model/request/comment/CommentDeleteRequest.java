package com.example.demo.model.request.comment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CommentDeleteRequest implements Serializable {
    private Long boardNo;
    private Long commentNo;
}
