package com.example.proyecto2.interfaces;

import com.example.proyecto2.domain.Category;

import java.util.List;

public interface ICategoryService {
    void createCategory(Category category);

    void deleteCategory(Long categoryId);

    Category getCategory(Long categoryId);

    void updateCategory(Category category);

    List<Category> getAllCategories();
}
