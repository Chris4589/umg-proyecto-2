package com.example.proyecto2.service;

import com.example.proyecto2.domain.Order;
import com.example.proyecto2.domain.ProductOrder;
import com.example.proyecto2.domain.ProductOrderPK;
import com.example.proyecto2.dto.CreateOrderDto;
import com.example.proyecto2.exception.NoContentException;
import com.example.proyecto2.interfaces.IOrderService;
import com.example.proyecto2.repository.ClientRepository;
import com.example.proyecto2.repository.OrderRepository;
import com.example.proyecto2.repository.ProductOrderRepository;
import com.example.proyecto2.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServices implements IOrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ProductOrderRepository productOrderRepository;

    @Override
    @Transactional
    public CreateOrderDto createOrder(CreateOrderDto orderDto) {
        orderDto.validate();
        orderDto.generateDefaultValues();

        clientRepository.findById(orderDto.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        var order = orderDto.toOrder();
        orderRepository.save(order);

        orderDto.getProducts().forEach(product -> {
            this.productRepository.findById(product)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            this.productOrderRepository.save(new ProductOrder(new ProductOrderPK(product, order.getOrderId())));
                });
        return orderDto;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getOrders() {
        var orders = this.orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new NoContentException("No orders found");
        }
        return orders;
    }

    @Override
    public Order updateOrderStatus(Order order) {
        this.orderRepository.findById(order.getOrderId())
                .orElseThrow(() -> new NoContentException("Order not found"));

        return this.orderRepository.updateOrderStatus(order.getStatus(), order.getOrderId());
    }
}
