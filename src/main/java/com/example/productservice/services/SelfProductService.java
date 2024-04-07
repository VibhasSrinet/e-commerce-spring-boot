package com.example.productservice.services;

import com.example.productservice.Models.Category;
import com.example.productservice.Models.Product;
import com.example.productservice.dtos.ProductNotFoundException;
import com.example.productservice.repositories.CategoryRepositories;
import com.example.productservice.repositories.ProductRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepositories productRepositories;
    private CategoryRepositories categoryRepositories;

    public SelfProductService(ProductRepositories productRepositories, CategoryRepositories categoryRepositories) {
        this.productRepositories = productRepositories;
        this.categoryRepositories = categoryRepositories;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepositories.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepositories.findAll();
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException{
        Optional<Product> productOptional = productRepositories.findById(id);

        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException("product with id "+id+" not found!");
        }

        return productRepositories.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepositories.findById(id);

        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException("product with id "+id+" not found!");
        }

        Product savedProduct = productOptional.get();

        if(product.getCategory()!= null){
            savedProduct.setCategory(product.getCategory());
        }

        if(product.getImage()!= null){
            savedProduct.setImage(product.getImage());
        }

        if(product.getTitle()!= null){
            savedProduct.setTitle(product.getTitle());
        }

        if(product.getDescription()!= null){
            savedProduct.setDescription(product.getDescription());
        }

        if(product.getPrice()!= null){
            savedProduct.setPrice(product.getPrice());
        }
        return productRepositories.save(savedProduct);
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Category> optionalCategory = categoryRepositories.findByName(product.getCategory().getName());
        if (optionalCategory.isEmpty()) {
            Category category=  categoryRepositories.save(product.getCategory());
            product.setCategory(category);
        }
        else{
            product.setCategory(optionalCategory.get());
        }
        return productRepositories.save(product);
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepositories.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("product with id " + id + " not found");
        }

        productRepositories.deleteById(id);
        return product.get();
    }
}
