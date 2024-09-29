package com.example.proyecto2.service;

import com.example.proyecto2.domain.Category;
import com.example.proyecto2.exception.BadRequestException;
import com.example.proyecto2.exception.NoContentException;
import com.example.proyecto2.interfaces.ICategoryService;
import com.example.proyecto2.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public void createCategory(Category category) {
        if (category == null) {
            throw new BadRequestException("Category is required");
        }
        category.validate();

        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        if (categoryId == null || categoryId <= 0) {
            throw new BadRequestException("Category ID is required");
        }

        var category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new NoContentException("Category not found");
        }

        categoryRepository.delete(category);
    }

    @Override
    public Category getCategory(Long categoryId) {
        if (categoryId == null || categoryId <= 0) {
            throw new BadRequestException("Category ID is required");
        }
        var category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new NoContentException("Category not found");
        }
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        if (category == null) {
            throw new BadRequestException("Category is required");
        }
        category.validate();
        category.validateCategoryId();

        var categoryToUpdate = categoryRepository.findById(category.getCategoryId()).orElse(null);
        if (categoryToUpdate == null) {
            throw new NoContentException("Category not found");
        }
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        var categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new NoContentException("No categories found");
        }
        return categories;
    }
}
