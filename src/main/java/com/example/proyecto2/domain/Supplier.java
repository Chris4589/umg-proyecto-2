package com.example.proyecto2.domain;

import com.example.proyecto2.exception.BadRequestException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "supplier")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    // Getters and setters were generated using Lombok with the @Getter and @Setter annotations
    public void validate() {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("Name is required");
        }
        if (address == null || address.isEmpty()) {
            throw new BadRequestException("Address is required");
        }
    }

    public void validateSupplierId() {
        if (supplierId == null || supplierId <= 0) {
            throw new BadRequestException("Supplier ID is required");
        }
    }
}
