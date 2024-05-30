package com.fresh_goodies_spring.fresh_goodies_spring.Products.Service;

import com.fresh_goodies_spring.fresh_goodies_spring.Cart.Model.CartItem;
import com.fresh_goodies_spring.fresh_goodies_spring.Products.model.Product;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


public interface ProductService {
    public List<Product> getProducts();
    public Optional<Product> getProduct(long id);
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public Boolean deleteProduct(Long id);
}
