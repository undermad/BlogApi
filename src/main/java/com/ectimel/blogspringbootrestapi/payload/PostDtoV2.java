package com.ectimel.blogspringbootrestapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Schema(
        description = "PostDtoV2 Model Information."
)
public class PostDtoV2 {
    private Long id;

    @Schema(description = "Blog post tittle.")
    @NotEmpty(message = "Can't be empty.")
    @Size(min = 2, message = "Post tittle should have at least 2 characters.")
    private String title;

    @Schema(description = "Blog post description.")
    @NotEmpty(message = "Can't be empty.")
    @Size(min = 10, message = "Description should have at least 10 characters.")
    private String description;

    @Schema(description = "Blog post content.")
    @NotEmpty(message = "Can't be empty.")
    private String content;

    private Set<CommentDto> comments;


    @Schema(description = "Category.")
    @Max(value = 5, message = "Category range is from 2-5: 2 - music \n3 - unknown \n")
    @Min(value = 2)
    private Long categoryId;

    @Schema(description = "Tags associated with post")
    private List<String> tags;

}
