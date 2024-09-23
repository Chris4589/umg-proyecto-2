package com.example.proyecto2.controller;

import com.example.proyecto2.domain.Product;
import com.example.proyecto2.dto.CreateProductDto;
import com.example.proyecto2.interfaces.IProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@NonNull @RequestBody CreateProductDto product) {
        return ResponseEntity.ok(this.productService.createProduct(product));
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(this.productService.getProducts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
