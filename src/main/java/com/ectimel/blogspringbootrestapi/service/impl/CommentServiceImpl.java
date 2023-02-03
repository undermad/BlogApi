package com.ectimel.blogspringbootrestapi.service.impl;

import com.ectimel.blogspringbootrestapi.entity.Comment;
import com.ectimel.blogspringbootrestapi.entity.Post;
import com.ectimel.blogspringbootrestapi.exception.ResourceNotFoundException;
import com.ectimel.blogspringbootrestapi.payload.CommentDto;
import com.ectimel.blogspringbootrestapi.repository.CommentRepository;
import com.ectimel.blogspringbootrestapi.repository.PostRepository;
import com.ectimel.blogspringbootrestapi.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToComment(commentDto);

        //retrieve post by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return mapToDto(savedComment);

    }

    private CommentDto mapToDto (Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setId(comment.getId());
        commentDto.setMessageBody(comment.getMessageBody());
        return commentDto;
    }

    private Comment mapToComment(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setId(commentDto.getId());
        comment.setMessageBody(commentDto.getMessageBody());

        return comment;
    }
}
