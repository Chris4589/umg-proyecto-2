package com.example.proyecto2.interfaces;

import com.example.proyecto2.domain.Order;
import com.example.proyecto2.dto.CreateOrderDto;
import com.example.proyecto2.dto.OrderResponseDto;

import java.util.List;


public interface IOrderService {
    CreateOrderDto createOrder(CreateOrderDto order);
    void deleteOrder(Long id);
    List<Order> getOrders();
    Order updateOrderStatus(Long orderId);

    OrderResponseDto getOrderById(Long orderId);
}
