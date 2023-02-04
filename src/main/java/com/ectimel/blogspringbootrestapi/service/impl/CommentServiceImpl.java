package com.ectimel.blogspringbootrestapi.service.impl;

import com.ectimel.blogspringbootrestapi.entity.Comment;
import com.ectimel.blogspringbootrestapi.entity.Post;
import com.ectimel.blogspringbootrestapi.exception.BlogAPIException;
import com.ectimel.blogspringbootrestapi.exception.ResourceNotFoundException;
import com.ectimel.blogspringbootrestapi.payload.CommentDto;
import com.ectimel.blogspringbootrestapi.repository.CommentRepository;
import com.ectimel.blogspringbootrestapi.repository.PostRepository;
import com.ectimel.blogspringbootrestapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
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

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());

    }

    @Override
    public CommentDto getCommentByPostIdAndId(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Post id or comment id not found.");
        }

        return mapToDto(comment);

    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment commentToUpdate = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!commentToUpdate.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Post id or comment id not found.");
        }

        commentToUpdate.setMessageBody(commentDto.getMessageBody());
        commentToUpdate.setName(commentDto.getName());
        commentToUpdate.setEmail(commentDto.getEmail());

        commentRepository.save(commentToUpdate);

        return mapToDto(commentToUpdate);

    }

    @Override
    public String deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Post id or comment id not found");
        }

        commentRepository.delete(comment);

        return "Comment with id: " + commentId + " has been deleted successful";
    }

    private CommentDto mapToDto (Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }

    private Comment mapToComment(CommentDto commentDto){
        return modelMapper.map(commentDto, Comment.class);
    }
}
