package com.example.productservice.services;

import com.example.productservice.Models.Product;
import com.example.productservice.dtos.ProductNotFoundException;

import java.util.List;

public interface ProductService  {
    Product getSingleProduct(Long id) throws ProductNotFoundException;
    List<Product> getAllProduct();

    Product replaceProduct(Long id, Product product) throws ProductNotFoundException;

    Product updateProduct(Long id, Product product) throws ProductNotFoundException;

    Product addProduct(Product product);

    Product deleteProduct(Long id) throws ProductNotFoundException;

}
