package com.example.proyecto2.repository;

import com.example.proyecto2.domain.SupplierProduct;
import com.example.proyecto2.domain.SupplierProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierProductRepository extends JpaRepository<SupplierProduct, SupplierProductPK> {
}
