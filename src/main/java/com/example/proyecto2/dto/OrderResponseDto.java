package com.example.proyecto2.dto;

import com.example.proyecto2.domain.Order;
import com.example.proyecto2.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto extends Order {
    private List<Product> products;

    public OrderResponseDto(Order order, List<Product> products) {
        this.setOrderId(order.getOrderId());
        this.setDate(order.getDate());
        this.setStatus(order.getStatus());
        this.setClientId(order.getClientId());
        this.setProducts(products);
    }
}
