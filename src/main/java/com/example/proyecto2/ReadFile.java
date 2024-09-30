package com.example.proyecto2;

import com.example.proyecto2.dto.CreateProductDto;
import com.example.proyecto2.interfaces.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@RequiredArgsConstructor
public class ReadFile implements CommandLineRunner {
    private final IProductService productService;
    @Override
    public void run(String... args) {
        System.out.println("Insert data to db from products.json");
        try {
            var resource = new ClassPathResource("products.json");
            var file = new File(resource.getFile().toURI());
            var om = new ObjectMapper();
            var products = om.readValue(file, CreateProductDto[].class);
            for (var product : products) {
                product.validate();
                var pExists = productService.getProductByName(product.getName());

                if (pExists == null) {
                    productService.createProduct(product);
                }
            }
        } catch (Exception e) {
            System.out.println("Error insertando data: " + e.getMessage());
        }
    }
}
