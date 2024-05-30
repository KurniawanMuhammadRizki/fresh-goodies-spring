package com.fresh_goodies_spring.fresh_goodies_spring.Cart;

import com.fresh_goodies_spring.fresh_goodies_spring.Cart.Model.CartItem;
import com.fresh_goodies_spring.fresh_goodies_spring.Cart.Service.CartService;
import com.fresh_goodies_spring.fresh_goodies_spring.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Response<List<CartItem>>> getCarts(){
        List<CartItem> carts = cartService.getCarts();
        if(carts.isEmpty()){
            return Response.successfulResponse("Cart is empty");
        }
        return Response.successfulResponse("All Cart item fetched", cartService.getCarts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<List<CartItem>>> getCart(@PathVariable Long id){
        var cartFound = cartService.getCart(id);
        if(cartFound.isEmpty()){
            return Response.failedResponse(HttpStatus.NOT_FOUND.value(), "Cart not found");
        }
        return Response.successfulResponse("Cart Found", cartFound);
    }

    @PutMapping
    public ResponseEntity<Response<CartItem>> updateCartItem(@RequestBody CartItem cartItem){
        return Response.successfulResponse("Update cart item success", cartService.updateCartItem(cartItem));
    }

    @PostMapping
    public ResponseEntity<Response<CartItem>> addToCart(@Validated @RequestBody CartItem cartItem){
        return Response.successfulResponse(HttpStatus.CREATED.value(), "Item added", cartService.addToCart(cartItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteCartItem(@PathVariable long id){
        boolean deletedCartItem = cartService.deleteCartItem(id);
        if (deletedCartItem) {
            return Response.successfulResponse("Cart Item deleted successfully");
        } else {
            return Response.failedResponse(HttpStatus.NOT_FOUND.value(), "Cart Item not found");
        }
    }
}
