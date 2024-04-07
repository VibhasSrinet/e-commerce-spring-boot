package com.example.productservice.services;

import com.example.productservice.Models.Category;
import com.example.productservice.Models.Product;
import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.dtos.ProductNotFoundDto;
import com.example.productservice.dtos.ProductNotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service("fakeProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public  FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto productDto){
        Product product=new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImage(product.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        return  product;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        FakeStoreProductDto productDto=  restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        if(productDto==null){
            throw new ProductNotFoundException("Product with " +id+" not found!");
        }
        return convertFakeStoreProductToProduct(productDto);
    }

    @Override
    public List<Product> getAllProduct() {
        FakeStoreProductDto[] response =restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto productDto: response){
            products.add(convertFakeStoreProductToProduct(productDto));
        }
        return products;
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException{
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response=  restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        System.out.println(response.getTitle());
        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotFoundException {
        return null;
    }
}
