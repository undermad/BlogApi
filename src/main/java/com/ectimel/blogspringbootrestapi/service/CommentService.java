package com.ectimel.blogspringbootrestapi.service;

import com.ectimel.blogspringbootrestapi.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
