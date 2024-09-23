package com.example.proyecto2.repository;

import com.example.proyecto2.domain.CategoryProduct;
import com.example.proyecto2.domain.CategoryProductPK;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProductRepository extends JpaRepository<CategoryProduct, CategoryProductPK> {

    @Modifying
    @Transactional
    @Query("DELETE FROM CategoryProduct cp WHERE cp.categoryProductPK.productId = ?1")
    void deleteByProductId(Long productId);
}
