package com.ectimel.blogspringbootrestapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
@Schema(description = "ErrorDetails Model Information")
public class ErrorDetails {
    @Schema(description = "Time when that error happened.")
    private Date timestamp;

    @Schema(description = "Error message")
    private String message;

    @Schema(description = "Details about this particular error.")
    private String details;

}
