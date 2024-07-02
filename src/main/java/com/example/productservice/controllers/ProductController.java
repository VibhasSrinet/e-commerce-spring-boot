package com.example.productservice.controllers;

import com.example.productservice.Models.Product;
import com.example.productservice.commons.AuthenticationCommons;
import com.example.productservice.dtos.*;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService, AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader("AuthenticationToken") String token) {
        UserDto userDto = authenticationCommons.validateToken(token);
        if(userDto == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        for(Role role: userDto.getRoles()){
           if(role.getRoleType().equals("ADMIN")){
                return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
           }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException{
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException{
        return productService.replaceProduct(id, product);
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException{
        return productService.deleteProduct(id);
    }
}
