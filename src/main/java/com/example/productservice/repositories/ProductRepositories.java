package com.example.productservice.repositories;

import com.example.productservice.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepositories extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    List<Product> findAll();
}
