package com.ectimel.blogspringbootrestapi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Data
public class PostDto {


    private Long id;

    @NotEmpty(message = "Can't be empty.")
    @Size(min = 2, message = "Post tittle should have at least 2 characters.")
    private String title;

    @NotEmpty(message = "Can't be empty.")
    @Size(min = 10, message = "Description should have at least 10 characters.")
    private String description;

    @NotEmpty(message = "Can't be empty.")
    private String content;


    private Set<CommentDto> comments;

}
