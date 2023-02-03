package com.ectimel.blogspringbootrestapi.repository;

import com.ectimel.blogspringbootrestapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
