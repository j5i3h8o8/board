package com.example.demo.model.request.board;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BoardDeleteRequest implements Serializable {
    private Long boardNo;
}
