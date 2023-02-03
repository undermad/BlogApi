package com.ectimel.blogspringbootrestapi.payload;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String messageBody;
}
