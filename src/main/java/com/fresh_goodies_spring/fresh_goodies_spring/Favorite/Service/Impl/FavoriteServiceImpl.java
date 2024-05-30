package com.fresh_goodies_spring.fresh_goodies_spring.Favorite.Service.Impl;

import com.fresh_goodies_spring.fresh_goodies_spring.Favorite.Model.Favorite;
import com.fresh_goodies_spring.fresh_goodies_spring.Favorite.Service.FavoriteService;
import com.fresh_goodies_spring.fresh_goodies_spring.Products.Service.ProductService;
import com.fresh_goodies_spring.fresh_goodies_spring.Products.model.Product;
import com.fresh_goodies_spring.fresh_goodies_spring.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService{
    private final ProductService productService;

    public FavoriteServiceImpl(ProductService productService){
        this.productService = productService;
    }

    List<Favorite> favorites = new ArrayList<>();

    @Override
    public List<Favorite> getFavorites(){
        return favorites;
    }
    @Override
    public List<Product> getFavoriteProducts() {
        return favorites.stream()
                .map(favorite -> productService.getProduct(favorite.getProductId())
                        .orElseThrow(() -> new DataNotFoundException("Product with ID " + favorite.getProductId() + " not found.")))
                .collect(Collectors.toList());
    }

}
