package com.ectimel.blogspringbootrestapi.payload;

import com.ectimel.blogspringbootrestapi.entity.Post;
import lombok.*;

@Data
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String messageBody;

}
