package com.example.proyecto2.repository;

import com.example.proyecto2.domain.ProductOrder;
import com.example.proyecto2.domain.ProductOrderPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderPK> {


}
