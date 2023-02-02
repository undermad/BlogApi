package com.ectimel.blogspringbootrestapi.service;

import com.ectimel.blogspringbootrestapi.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto, Long id);
    String delete(Long id);
}
