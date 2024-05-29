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

//    public CartItem addToCart(CartItem cartItem){
//        Optional<Product> productExist = productService.getProduct(cartItem.getProductId());
//        if(productExist.isPresent()) {
//
//            long newId = carts.stream().mapToLong(CartItem::getId).max().orElse(0L) + 1;
//            cartItem.setId(newId);
//            carts.add(cartItem);
//            return cartItem;
//        } else {
//            throw new ApplicationException("Product with ID " + cartItem.getId() + " is not exists.");
//        }
//    }

    public CartItem addToCart(CartItem cartItem) {
        Optional<Product> productExist = productService.getProduct(cartItem.getProductId());
        if (productExist.isPresent()) {
            Optional<CartItem> existingCartItemOpt = carts.stream()
                    .filter(c -> c.getProductId() == cartItem.getProductId())
                    .findFirst();
            if (existingCartItemOpt.isPresent()) {
                CartItem existingCartItem = existingCartItemOpt.get();
                existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
                return existingCartItem;
            } else {
                long newId = carts.stream().mapToLong(CartItem::getId).max().orElse(0L) + 1;
                cartItem.setId(newId);
                carts.add(cartItem);
                return cartItem;
            }
        } else {
            throw new ApplicationException("Product with ID " + cartItem.getProductId() + " does not exist.");
        }
    }

    public CartItem updateCartItem(CartItem cartItem) {
        Optional<CartItem> currentCartItemOpt = carts.stream().filter(p -> p.getId() == cartItem.getId()).findFirst();
        if (currentCartItemOpt.isPresent()) {
            CartItem currentCartItem = currentCartItemOpt.get();
            currentCartItem.setProductId(cartItem.getProductId());
            currentCartItem.setQuantity(cartItem.getQuantity());
            return currentCartItem;
        } else {
            throw new NoSuchElementException("Cart Item with ID " + cartItem.getId() + " not found");
        }
    }


    public Boolean deleteCartItem(Long id) {
        List<CartItem> cartItems = getCart(id);
        if (!cartItems.isEmpty()) {
            carts.removeAll(cartItems);
            return true;
        } else {
            return false;
        }
    }

}
