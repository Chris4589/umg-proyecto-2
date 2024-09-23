package com.example.proyecto2.domain;

import com.example.proyecto2.exception.BadRequestException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    public void validate() {
        if (this.name == null || this.name.isBlank()) {
            throw new BadRequestException("Name is required");
        }
        if (this.price == null || this.price <= 0) {
            throw new BadRequestException("Price is required");
        }
    }
    // Getters and setters were generated using Lombok with the @Getter and @Setter annotations
}
