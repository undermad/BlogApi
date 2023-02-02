package com.ectimel.blogspringbootrestapi.repository;

import com.ectimel.blogspringbootrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
