package com.fresh_goodies_spring.fresh_goodies_spring.Cart;

import com.fresh_goodies_spring.fresh_goodies_spring.Cart.Model.CartItem;
import com.fresh_goodies_spring.fresh_goodies_spring.Cart.Service.CartService;
import com.fresh_goodies_spring.fresh_goodies_spring.responses.Response;
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
        return Response.successfulResponse("All Cart item fetched"); // ini belom
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Optional<CartItem>>> getCart(@PathVariable Long id){
//        var cartFound =
        return Response.successfulResponse("Cart Found");
    }

//    @PutMapping
//    public ResponseEntity<Response<CartItem>> updateCart(@RequestBody)

    @PostMapping
    public ResponseEntity<Response<CartItem>> addToCart(@Validated @RequestBody CartItem cartItem){
        return Response.successfulResponse("Item Added");
    }


}
