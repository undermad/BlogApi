package com.ectimel.blogspringbootrestapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "PostResponse Model Information")
public class PostResponse {

    @Schema(description = "List of comments")
    private List<PostDto> content;

    @Schema(description = "Current page")
    private int pageNo;

    @Schema(description = "Maximum comment that can show on one page.")
    private int pageSize;

    @Schema(description = "Amount of comments on all pages.")
    private long totalElements;

    @Schema(description = "Number of pages.")
    private int totalPages;

    @Schema(description = "True if last page.")
    private boolean last;
}
