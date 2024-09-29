package com.example.proyecto2.domain;

import com.example.proyecto2.exception.BadRequestException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    // Getters and setters were generated using Lombok with the @Getter and @Setter annotations
    public void validate() {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("Name is required");
        }
        if (description == null || description.isEmpty()) {
            throw new BadRequestException("Description is required");
        }
    }
    public void validateCategoryId() {
        if (categoryId == null || categoryId <= 0) {
            throw new BadRequestException("Category ID is required");
        }
    }
}
