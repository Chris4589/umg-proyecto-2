package com.example.proyecto2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SupplierProductPK implements Serializable {
    @Column(name = "supplier_id")
    private Long supplierId;
    @Column(name = "product_id")
    private Long productId;

    // Getters and setters were generated using Lombok with the @Getter and @Setter annotations
}
