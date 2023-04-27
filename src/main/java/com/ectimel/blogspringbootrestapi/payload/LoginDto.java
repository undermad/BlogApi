package com.ectimel.blogspringbootrestapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "LoginDto Model Information")
public class LoginDto {
    @Schema(description = "Username or Email used in registration form.")
    private String usernameOrEmail;

    @Schema(description = "Password used in registration form.")
    private String password;

}
