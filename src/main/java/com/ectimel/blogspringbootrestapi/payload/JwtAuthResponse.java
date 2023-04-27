package com.ectimel.blogspringbootrestapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "JwtAuthResponse Model Information")
public class JwtAuthResponse {

    @Schema(description = "Access token.")
    private String accessToken;

    @Schema(description = "Bearer token type.")
    private String tokenType = "Bearer";
}
