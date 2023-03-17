package com.ectimel.blogspringbootrestapi.payload;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotEmpty(message = "Can't be empty.")
    private String name;

    @NotEmpty(message = "Can't be empty.")
    @Email(message = "Email need to be well formatted.")
    private String email;

    @NotEmpty(message = "Can't be empty.")
    private String username;


    @NotEmpty(message = "Can't be empty.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$]).{8,20}$",
            message = "Password must have at least one uppercase character," +
                    " one lowercase character, one numeric character," +
                    " special symbol -'!@#$%'," +
                    " length between 8 and 20 characters.")
    private String password;

}
