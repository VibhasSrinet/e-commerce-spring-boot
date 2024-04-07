package com.example.productservice.repositories;

import com.example.productservice.Models.Category;
import com.example.productservice.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositories extends JpaRepository<Category, Long> {
    Category save(Category category);

    Optional<Category> findByName(String name);
}
