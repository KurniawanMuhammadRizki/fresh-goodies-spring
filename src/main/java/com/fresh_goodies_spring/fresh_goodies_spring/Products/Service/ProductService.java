package com.fresh_goodies_spring.fresh_goodies_spring.Products.Service;

import com.fresh_goodies_spring.fresh_goodies_spring.Products.model.Product;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Service
public class ProductService {
    List<Product> products = new ArrayList<>();

    public Optional<Product> getProduct(long id){
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }

    public Product addProduct(Product product){
        long newId = products.stream().mapToLong(Product::getId).max().orElse(0L) + 1;
        product.setId(newId);
        products.add(product);
        return product;
    }

    public Product updateProduct(Product product){
        Product currentProduct = products.stream().filter(p -> p.getId() == product.getId()).findFirst().orElse(null);

        if(currentProduct != null){
            currentProduct.setName(product.getName());
            currentProduct.setCategory(product.getCategory());
            currentProduct.setImageUrl(product.getImageUrl());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setWeight(product.getWeight());
            currentProduct.setMetadata(product.getMetadata());
        }
        return currentProduct;
    }

    public Boolean deleteProduct(Long id){
        Optional<Product> productOptional = getProduct(id);
        if (productOptional.isPresent()) {
            products.remove(productOptional.get());
            return true;
        } else {
         return false;
        }
    }
}
