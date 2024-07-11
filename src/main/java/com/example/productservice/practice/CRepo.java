package com.example.productservice.practice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CRepo extends JpaRepository<C, Long> {

    C save(C c);
}
