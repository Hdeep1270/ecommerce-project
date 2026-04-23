package com.ecommerce.project.repository;

import com.ecommerce.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepository extends JpaRepository<Category, Long> {
}
