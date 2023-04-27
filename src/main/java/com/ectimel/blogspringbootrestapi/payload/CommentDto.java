package com.ectimel.blogspringbootrestapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Schema(description = "CommentDto Model Information")
public class CommentDto {
    private Long id;

    @Schema(description = "Author name")
    @NotEmpty(message = "Can't be empty")
    private String name;

    @Schema(description = "Author email")
    @NotEmpty(message = "Can't be empty.")
    @Email(message = "Email need to be well formatted.")
    private String email;

    @Schema(description = "Comment content")
    @NotEmpty(message = "Can't be empty")
    @Size(min = 1, message = "Should have at least 2 characters.")
    private String messageBody;

}
