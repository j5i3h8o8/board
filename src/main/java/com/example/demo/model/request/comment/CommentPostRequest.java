package com.example.demo.model.request.comment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CommentPostRequest implements Serializable {
    private Long boardNo;
    private String commentBody;
}
