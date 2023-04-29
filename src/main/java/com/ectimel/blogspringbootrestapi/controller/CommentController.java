package com.ectimel.blogspringbootrestapi.controller;

import com.ectimel.blogspringbootrestapi.payload.CommentDto;
import com.ectimel.blogspringbootrestapi.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts/{postId}")
@Tag(name = " Comment resource V1")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(
            summary = "Create comment",
            description = "Create comment that belong to specific post id and store it in the database.")
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED")
    @PostMapping("/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get all post's comments.",
            description = "Get all comments that belong to post with given id.")
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS")
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable(name = "postId") long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }


    @Operation(
            summary = "Get comment by id",
            description = "Get comment with given id that belong to post with given id.")
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS")
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable(name = "postId") Long postId,
                                                 @PathVariable(name = "commentId") Long commentId) {
        return ResponseEntity.ok(commentService.getCommentByPostIdAndId(postId, commentId));
    }


    @Operation(
            summary = "Update comment",
            description = "Update comment with given id that belong to post with given id.")
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS")
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "postId") Long postId,
                                                    @PathVariable(name = "commentId") Long commentId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }


    @Operation(
            summary = "Delete comment by id",
            description = "Delete comment with given id from post with given id.")
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS")
    @DeleteMapping
    public ResponseEntity<String> deleteComment(@PathVariable(name = "postId") Long postId,
                                                @PathVariable(name = "commentId") Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(postId, commentId));
    }

}
