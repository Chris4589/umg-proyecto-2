package com.example.proyecto2.interfaces;

import com.example.proyecto2.domain.Product;
import com.example.proyecto2.dto.CreateProductDto;

import java.util.List;

public interface IProductService {
    void deleteProduct(Long id);
    List<Product> getProducts();
    Product createProduct(CreateProductDto product);
    Product updateProduct(CreateProductDto product);
}
