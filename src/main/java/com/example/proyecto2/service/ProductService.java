package com.example.proyecto2.service;

import com.example.proyecto2.domain.CategoryProduct;
import com.example.proyecto2.domain.CategoryProductPK;
import com.example.proyecto2.domain.Product;
import com.example.proyecto2.dto.CreateProductDto;
import com.example.proyecto2.exception.NoContentException;
import com.example.proyecto2.interfaces.IProductService;
import com.example.proyecto2.repository.CategoryProductRepository;
import com.example.proyecto2.repository.CategoryRepository;
import com.example.proyecto2.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryProductRepository categoryProductRepository;

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        this.categoryProductRepository.deleteByProductId(id);
        this.productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProducts() {
        var products = this.productRepository.findAll();
        if (products.isEmpty()) {
            throw new NoContentException("No products found");
        }

        return products;
    }

    @Override
    @Transactional
    public Product createProduct(CreateProductDto productDto) {
        productDto.validate();

        var product = productDto.toProduct();
        this.productRepository.save(product);

        productDto.getCategories().forEach(categoryId -> {
            this.categoryRepository.findById(categoryId).orElseThrow(() -> new NoContentException("Category not found"));
            this.categoryProductRepository.save(new CategoryProduct(new CategoryProductPK(categoryId, product.getProductId())));
        });

        return product;
    }

    @Override
    public Product updateProduct(CreateProductDto product) {
        return null;
    }
}
