package com.ecommerce.project.service;
import com.ecommerce.project.model.Category;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface categoryService {
    List<Category> getAllCategories();
    String createCategory(@RequestBody Category category);
    String deleteCategory(Long categoryId);
    Category updateCategory(Category category, Long categoryId);
}
