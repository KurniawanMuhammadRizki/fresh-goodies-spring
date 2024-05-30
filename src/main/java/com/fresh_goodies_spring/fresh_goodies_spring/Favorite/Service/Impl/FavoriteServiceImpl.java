package com.fresh_goodies_spring.fresh_goodies_spring.Favorite.Service.Impl;

import com.fresh_goodies_spring.fresh_goodies_spring.Favorite.Model.Favorite;
import com.fresh_goodies_spring.fresh_goodies_spring.Favorite.Service.FavoriteService;
import com.fresh_goodies_spring.fresh_goodies_spring.Products.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}
