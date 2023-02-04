package com.ectimel.blogspringbootrestapi.payload;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;

}
