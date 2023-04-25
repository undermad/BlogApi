package com.ectimel.blogspringbootrestapi.repository;

import com.ectimel.blogspringbootrestapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
