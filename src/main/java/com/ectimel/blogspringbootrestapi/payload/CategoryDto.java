package com.ectimel.blogspringbootrestapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "CategoryDto Model Information"
)
public class CategoryDto {
    private Long id;

    @NotEmpty(message = "Can't be empty.")
    @Size(min = 2, message = "Category name should have at least 2 characters.")
    @Schema(description = "Category name")
    private String name;

    @Schema(description = "Category description")
    private String description;
}
