package com.example.proyecto2.interfaces;

import com.example.proyecto2.domain.Order;
import com.example.proyecto2.dto.CreateOrderDto;

import java.util.List;


public interface IOrderService {
    CreateOrderDto createOrder(CreateOrderDto order);
    void deleteOrder(Long id);
    List<Order> getOrders();
    Order updateOrderStatus(Order order);
}
