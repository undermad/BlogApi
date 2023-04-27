package com.ectimel.blogspringbootrestapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "RegisterDto Model Information")
public class RegisterDto {

    @Schema(description = "Your name")
    @NotEmpty(message = "Can't be empty.")
    private String name;

    @Schema(description = "Your email address")
    @NotEmpty(message = "Can't be empty.")
    @Email(message = "Email need to be well formatted.")
    private String email;

    @Schema(description = "Your username")
    @NotEmpty(message = "Can't be empty.")
    private String username;


    @Schema(description = "Password must have at least one uppercase character," +
            " one lowercase character, one numeric character," +
            " special symbol -'!@#$%'," +
            " length between 8 and 20 characters.")
    @NotEmpty(message = "Can't be empty.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$]).{8,20}$",
            message = "Password must have at least one uppercase character," +
                    " one lowercase character, one numeric character," +
                    " special symbol -'!@#$%'," +
                    " length between 8 and 20 characters.")
    private String password;

}
