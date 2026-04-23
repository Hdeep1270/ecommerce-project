package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class categoryServiceImpl implements categoryService {

    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;
    @Autowired
    private categoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    @Override
    public String createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoryRepository.save(category);
        return "New category added";
    }


    @Override
    public String deleteCategory(Long categoryId) {
        List<Category> categoryList = categoryRepository.findAll();

        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));

        categoryRepository.delete(category);
        return "category with categoryID" + categoryId + " has been deleted.";
    }



    @Override
    public Category updateCategory(Category category, Long categoryId) {
        List<Category> categoryList = categoryRepository.findAll();

        Optional<Category> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();
        if(optionalCategory.isPresent()){
            Category categoryToUpdate = optionalCategory.get();
            categoryToUpdate.setCategoryName(category.getCategoryName());
            return categoryRepository.save(category);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found");
        }
    }


}
