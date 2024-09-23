package com.example.proyecto2.controller;

import com.example.proyecto2.dto.CreateOrderDto;
import com.example.proyecto2.dto.OrderResponseDto;
import com.example.proyecto2.service.OrderServices;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServices orderServices;
    @PostMapping("/")
    public ResponseEntity<CreateOrderDto> createOrder(@NonNull @RequestBody CreateOrderDto createOrderDto) {
        return ResponseEntity.ok(this.orderServices.createOrder(createOrderDto));
    }
}
