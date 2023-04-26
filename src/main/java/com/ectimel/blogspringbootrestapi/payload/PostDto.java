package com.ectimel.blogspringbootrestapi.payload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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


    @Max(value = 5, message = "Category range is from 2-5: 2 - music \n3 - unknown \n")
    @Min(value = 2)
    private Long categoryId;

}
