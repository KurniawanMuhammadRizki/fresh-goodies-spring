package com.fresh_goodies_spring.fresh_goodies_spring.Favorite.Service;

import com.fresh_goodies_spring.fresh_goodies_spring.Cart.Model.CartItem;
import com.fresh_goodies_spring.fresh_goodies_spring.Favorite.Model.Favorite;
import com.fresh_goodies_spring.fresh_goodies_spring.Products.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface FavoriteService {
    List<Favorite> favorites = new ArrayList<>();
    public List<Favorite> getFavorites();
    public List<Product> getFavoriteProducts();
    public boolean toggleFavorite(Favorite favorite);
}
