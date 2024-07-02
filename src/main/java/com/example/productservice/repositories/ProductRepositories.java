package com.example.productservice.repositories;

import com.example.productservice.Models.Product;
import com.example.productservice.repositories.projections.ProductWithIdAnsDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepositories extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    List<Product> findAll();

    @Query("SELECT p from Product p where p.id= ?1 and p.description like concat('%',?2,'%') ")
    List<ProductWithIdAnsDescription> getJustIdAndDescription(Long id, String description);

    @Query(value = "SELECT p.id as id, p.description as description from product p where p.id= :id and p.description like concat('%',:description,'%') ", nativeQuery = true)
    List<ProductWithIdAnsDescription> getJustIdAndDescriptionViaNative(@Param("id") Long id,@Param("description") String description);
}
