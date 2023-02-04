package com.ectimel.blogspringbootrestapi.service;

import com.ectimel.blogspringbootrestapi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(Long postId);
    CommentDto getCommentByPostIdAndId(Long postId, Long commentId);
    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);
    String deleteComment(Long postId, Long commentId);

}
