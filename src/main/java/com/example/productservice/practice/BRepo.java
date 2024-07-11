package com.example.productservice.practice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BRepo extends JpaRepository<B, Long> {
    B save(B b);
    Optional<B> findById(Long id);
    void deleteById(Long id);
}
