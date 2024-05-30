package com.fresh_goodies_spring.fresh_goodies_spring.Cart.Service;

import com.fresh_goodies_spring.fresh_goodies_spring.Cart.Model.CartItem;
import com.fresh_goodies_spring.fresh_goodies_spring.Products.Service.ProductService;
import com.fresh_goodies_spring.fresh_goodies_spring.Products.model.Product;
import com.fresh_goodies_spring.fresh_goodies_spring.exceptions.ApplicationException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public interface CartService {
    List<CartItem> carts = new ArrayList<>();
    public List<CartItem> getCarts();
    public List<CartItem> getCart(long id);
    public CartItem addToCart(CartItem cartItem);
    public CartItem updateCartItem(CartItem cartItem);
    public Boolean deleteCartItem(Long id);
}
