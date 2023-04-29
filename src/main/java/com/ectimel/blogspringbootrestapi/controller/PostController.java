package com.ectimel.blogspringbootrestapi.controller;

import com.ectimel.blogspringbootrestapi.payload.PostDto;
import com.ectimel.blogspringbootrestapi.payload.PostResponse;
import com.ectimel.blogspringbootrestapi.service.PostService;
import com.ectimel.blogspringbootrestapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/posts")
@Tag(
        name = "Post resource V1"
)
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @Operation(
            summary = "Create Post",
            description = "Create Post is used to save post into database.")
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all posts",
            description = "Get all posts existing in database.")
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS")
    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ) {
        return new ResponseEntity<>(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @Operation(
            summary = "Get post by id",
            description = "Get post with given id from database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @Operation(
            summary = "Get all posts belong to given category id",
            description = "Get all posts from that belong to given category id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategoryId(@PathVariable(name = "id") Long categoryId){
        List<PostDto> posts = postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(posts);
    }

    @Operation(
            summary = "Update post",
            description = "Update post with given id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.updatePost(postDto, id));
    }

    @Operation(
            summary = "Delete post",
            description = "Delete post with given id from database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.delete(id));
    }

}
