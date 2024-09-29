package com.example.proyecto2.repository;

import com.example.proyecto2.domain.Product;
import com.example.proyecto2.domain.ProductOrder;
import com.example.proyecto2.domain.ProductOrderPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderPK> {

    @Query(nativeQuery = true, value = "SELECT p.* FROM product p INNER JOIN product_order po ON p.product_id = po.product_id WHERE po.order_id = ?1")
    List<Object[]> findProductsByOrderId(Long orderId);

}
