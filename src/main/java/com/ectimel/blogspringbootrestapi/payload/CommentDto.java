package com.ectimel.blogspringbootrestapi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class CommentDto {
    private Long id;

    @NotEmpty(message = "Can't be empty")
    private String name;

    @NotEmpty(message = "Can't be empty.")
    @Email(message = "Email need to be well formatted.")
    private String email;

    @NotEmpty(message = "Can't be empty")
    @Size(min = 1, message = "Should have at least 2 characters.")
    private String messageBody;

}
