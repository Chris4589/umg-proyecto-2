package com.example.proyecto2.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productOrder")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductOrder {
    @EmbeddedId
    private ProductOrderPK productOrderPK;
    // Getters and setters were generated using Lombok with the @Getter and @Setter annotations
}
