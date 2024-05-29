package com.fresh_goodies_spring.fresh_goodies_spring.Cart.Service;

import com.fresh_goodies_spring.fresh_goodies_spring.Cart.Model.CartItem;
import com.fresh_goodies_spring.fresh_goodies_spring.Products.Service.ProductService;
import com.fresh_goodies_spring.fresh_goodies_spring.Products.model.Product;
import com.fresh_goodies_spring.fresh_goodies_spring.exceptions.ApplicationException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class CartService {
    private final ProductService productService;
    public CartService(ProductService productService){
        this.productService = productService;
    }
    List<CartItem> carts = new ArrayList<>();

    public List<CartItem> getCarts(){
        return carts;
    }

    public List<CartItem> getCart(long id){
        List<CartItem> result = new ArrayList<>();
        for (CartItem cartItem : carts) {
            if (cartItem.getId() == id) {
                result.add(cartItem);
            }
        }
        return result;
    }

    public CartItem addToCart(CartItem cartItem){
        Optional<Product> productExist = productService.getProduct(cartItem.getProductId());
        if(productExist.isPresent()) {
            carts.add(cartItem);
            return cartItem;
        } else {
            throw new ApplicationException("Product with ID " + cartItem.getId() + " is not exists.");
        }
    }

//    public Boolean deleteCartItem(Long id){
//
//    }
}
