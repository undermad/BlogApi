package com.ectimel.blogspringbootrestapi.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String messageBody;
}
