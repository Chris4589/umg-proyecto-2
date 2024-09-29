package com.example.proyecto2.controller;

import com.example.proyecto2.domain.Category;
import com.example.proyecto2.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<Void> createCategory(@RequestBody Category category) {
        this.categoryService.createCategory(category);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateCategory(@RequestBody Category category) {
        this.categoryService.updateCategory(category);
        return ResponseEntity.ok().build();
    }
}
