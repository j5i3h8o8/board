package com.example.demo.model.request.board;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BoardPostRequest implements Serializable {
    private String title;
    private String body;
}
