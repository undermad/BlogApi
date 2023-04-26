package com.ectimel.blogspringbootrestapi.service.impl;

import com.ectimel.blogspringbootrestapi.entity.Category;
import com.ectimel.blogspringbootrestapi.entity.Post;
import com.ectimel.blogspringbootrestapi.exception.ResourceNotFoundException;
import com.ectimel.blogspringbootrestapi.payload.PostDto;
import com.ectimel.blogspringbootrestapi.payload.PostResponse;
import com.ectimel.blogspringbootrestapi.repository.CategoryRepository;
import com.ectimel.blogspringbootrestapi.repository.PostRepository;
import com.ectimel.blogspringbootrestapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private CategoryRepository categoryRepository;


    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Category category = categoryRepository
                .findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", postDto.getCategoryId()));


        Post post = mapToPost(postDto);
        post.setCategory(category);
        Post newPost = postRepository.save(post);
        return mapToDTO(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content = listOfPosts.stream().map(this::mapToDTO).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.getComments().forEach(comment -> System.out.println(comment.getName()));
        System.out.println("hereeee");
        return mapToDTO(post);
    }


    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {
        categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        List<Post> posts = postRepository.findByCategoryId(categoryId);
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        Category category = categoryRepository
                .findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", postDto.getCategoryId()));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);

        postRepository.save(post);
        return mapToDTO(post);
    }

    @Override
    public String delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
        return "Post with id: " + id + " has been deleted.";

    }


    private PostDto mapToDTO(Post post) {
        return modelMapper.map(post, PostDto.class);
    }

    private Post mapToPost(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

}
