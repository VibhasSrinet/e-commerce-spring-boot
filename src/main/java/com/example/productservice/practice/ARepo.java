package com.example.productservice.practice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ARepo extends JpaRepository<A, Long> {
    A save(A a);
    Optional<A> findById(Long id);
    void deleteById(Long id);
}
