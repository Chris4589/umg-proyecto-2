package com.example.proyecto2.domain;

import com.example.proyecto2.exception.BadRequestException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private String status;

    @Column(name = "client_id")
    private Long clientId;

    public void validate() {
        if (this.clientId == null || this.clientId < 1) {
            throw new BadRequestException("Client ID is required");
        }
    }

    public void generateDefaultValues() {
        this.date = new Date();
        this.status = "Pendiente";
    }
    // Getters and setters were generated using Lombok with the @Getter and @Setter annotations
}
