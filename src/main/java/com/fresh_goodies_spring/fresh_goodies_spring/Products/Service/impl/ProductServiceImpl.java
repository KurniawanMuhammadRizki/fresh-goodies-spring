package com.fresh_goodies_spring.fresh_goodies_spring.Products.Service.impl;

import com.fresh_goodies_spring.fresh_goodies_spring.Products.Service.ProductService;
import com.fresh_goodies_spring.fresh_goodies_spring.Products.model.Product;
import com.fresh_goodies_spring.fresh_goodies_spring.exceptions.ApplicationException;
import com.fresh_goodies_spring.fresh_goodies_spring.exceptions.DataNotFoundException;
import com.fresh_goodies_spring.fresh_goodies_spring.exceptions.EmptyDataException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
public class ProductServiceImpl implements ProductService {
    List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getProducts(){
        return products;
    }


    @Override
    public List<Product> searchProducts(String query) {
        if (query == null || query.isEmpty()) {
            if(products.isEmpty()){
                throw new EmptyDataException(HttpStatus.OK, "Product Empty");
            }
            return products;
        }
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getProduct(long id){
        Optional<Product> exist = products.stream().filter(product -> product.getId() == id).findFirst();
        if(exist.isPresent()){
            return exist;
        }
        //throw  new ApplicationException(HttpStatus.NOT_FOUND, "Product with ID " + id + "not found");
        throw new DataNotFoundException("Product with ID " + id + " not found.");
    }

    @Override
    public Product addProduct(Product product){
        long newId = products.stream().mapToLong(Product::getId).max().orElse(0L) + 1;
        product.setId(newId);
        products.add(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> currentProductOpt = products.stream().filter(p -> p.getId() == product.getId()).findFirst();

        if (currentProductOpt.isPresent()) {
            Product currentProduct = currentProductOpt.get();
            currentProduct.setName(product.getName());
            currentProduct.setCategory(product.getCategory());
            currentProduct.setImageUrl(product.getImageUrl());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setWeight(product.getWeight());
            currentProduct.setMetadata(product.getMetadata());
            return currentProduct;
        } else {
            throw new DataNotFoundException("Product with ID " + product.getId() + " not found");
        }
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> productOptional = getProduct(id);
        if (productOptional.isPresent()) {
            products.remove(productOptional.get());
            return productOptional.get();
        }
        throw new DataNotFoundException("Product with ID " + id + " not found.");
    }
}
