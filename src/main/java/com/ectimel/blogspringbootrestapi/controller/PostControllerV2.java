package com.ectimel.blogspringbootrestapi.controller;

import com.ectimel.blogspringbootrestapi.payload.PostDto;
import com.ectimel.blogspringbootrestapi.payload.PostDtoV2;
import com.ectimel.blogspringbootrestapi.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v2/posts")
@Tag(
        name = "Post resource V2"
)
public class PostControllerV2 {
//  *****************************************************************************************
//  IN THIS EXAMPLE NEW FIELD `TAGS` IS HARDCODED AND IT'S HERE ONLY FOR REFERENCE FOR FUTURE
//  IN REAL LIFE WE NEED TO REWRITE AND ADD EXTRA FUNCTIONS TO SUPPORT NEW VERSION AND OLD AS WELL


//  We can add also query param as versioning implementation. For example in @GetMapping("/{id}", params = "version=1")
//  adding ?version=1 in url give as flexibility which method we use



//  This approach to implement versioning is by headers.
//  Pros: We don't need to change url address
//  Cons: We need to write custom headers
//  Example:
//    @GetMapping(value = "/{id}", headers = "x-api-version=1")

//    http://localhost:8080/api/products
//    header=[x-api-version=1]

//    1. Versioning through URI Path
//    One way to version a REST API is to include the version number in the URI path.
//
//            Examples:
//
//    http://www.example.com/api/1/products http://www.example.com/api/v1/products http://www.example.com/api/v2/products http://www.example.com/api/v1/posts http://www.example.com/api/v1/employees
//    2. Versioning through query parameters
//    Another option for versioning a REST API is to include the version number as a query parameter.
//
//    Examples:
//
//    http://www.example.com/api/products?version=1 http://www.example.com/api/products?version=2 http://www.example.com/api/posts?version=1 http://www.example.com/api/employees?version=1
//
//    3. Versioning through custom headers
//    REST APIs can also be versioned by providing custom headers with the version number included as an attribute.
//
//    The main difference between this approach and the two previous ones is that it doesn’t clutter the URI with versioning information.
//
//            Examples:
//
//    http://localhost:8080/api/products headers=[X-API-VERSION=1]
//
//    http://localhost:8080/api/products headers=[X-API-VERSION=2]
//
//    Pros: It doesn’t clutter the URI with versioning information
//
//    Cons: It requires custom headers
//
//    4. Versioning through content negotiation
//    The last strategy we are addressing is versioning through content negotiation.
//
//            In this approach, we use the Accept header in the request.
//
//    Examples:
//
//    http://localhost:8080/api/products headers[Accept=application/vnd.javaguides-v1+json] http://localhost:8080/api/products headers[Accept=application/vnd.javaguides-v2+json]
//

//  *****************************************************************************************

    private PostService postService;
    private ModelMapper modelMapper;

    public PostControllerV2(PostService postService, ModelMapper modelMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDtoV2> getPostById(@PathVariable Long id) {
        PostDto postDto = postService.getPostById(id);
        PostDtoV2 postDtoV2 = modelMapper.map(postDto, PostDtoV2.class);
        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("Spring Boot");
        postDtoV2.setTags(tags);

        return ResponseEntity.ok(postDtoV2);
    }
}
