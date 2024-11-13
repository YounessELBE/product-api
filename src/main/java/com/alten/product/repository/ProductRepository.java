package com.alten.product.repository;

import com.alten.product.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCode(String code);

    Optional<Object> findByCodeAndIdNot(String code, Long id);
}
