package com.example.proyecto2.service;

import com.example.proyecto2.domain.Order;
import com.example.proyecto2.domain.Product;
import com.example.proyecto2.domain.ProductOrder;
import com.example.proyecto2.domain.ProductOrderPK;
import com.example.proyecto2.dto.CreateOrderDto;
import com.example.proyecto2.dto.OrderResponseDto;
import com.example.proyecto2.exception.NoContentException;
import com.example.proyecto2.interfaces.IOrderService;
import com.example.proyecto2.repository.ClientRepository;
import com.example.proyecto2.repository.OrderRepository;
import com.example.proyecto2.repository.ProductOrderRepository;
import com.example.proyecto2.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Order updateOrderStatus(Long orderId) {
        var order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new NoContentException("Order not found"));

        order.setStatus("DELIVERED");

        this.orderRepository.updateOrderStatus(order.getStatus(), order.getOrderId());
        
        return order;
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        var order = this.orderRepository.findById(orderId).orElse(null);

        if (order == null) {
            throw new NoContentException("Order not found");
        }
        var tmp = this.productOrderRepository.findProductsByOrderId(orderId);

        var products = new ArrayList<Product>();
        for (Object[] row : tmp) {
            Long productId = ((Number) row[0]).longValue();
            String name = (String) row[1];
            Double price = (Double) row[2];

            var product = new Product();
            product.setProductId(productId);
            product.setName(name);
            product.setPrice(price);

            products.add(product);
        }

        if (products.isEmpty()) {
            throw new NoContentException("No products found for this order");
        }

        return new OrderResponseDto(order, products);
    }
}
