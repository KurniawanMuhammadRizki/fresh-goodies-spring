package com.fresh_goodies_spring.fresh_goodies_spring.Favorite;

import com.fresh_goodies_spring.fresh_goodies_spring.Favorite.Model.Favorite;
import com.fresh_goodies_spring.fresh_goodies_spring.Favorite.Service.FavoriteService;
import com.fresh_goodies_spring.fresh_goodies_spring.Products.model.Product;
import com.fresh_goodies_spring.fresh_goodies_spring.exceptions.DataNotFoundException;
import com.fresh_goodies_spring.fresh_goodies_spring.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }

//    @GetMapping
//    public ResponseEntity<Response<List<Favorite>>> getFavorites(){
//        List<Favorite> favoriteList = favoriteService.getFavorites();
//
//        if(favoriteList.isEmpty()){
//            return Response.successfulResponse("Favorite Empty");
//        }
//        return Response.successfulResponse("All favorites fetched", favoriteList);
//    }
    @GetMapping
    public ResponseEntity<Response<List<Product>>> getFavorites() {
        List<Product> favoriteProducts = favoriteService.getFavoriteProducts();
        if (favoriteProducts.isEmpty()) {
            return Response.failedResponse(HttpStatus.NOT_FOUND.value(), "No favorite products found");
        }
        return Response.successfulResponse("Favorite products fetched successfully", favoriteProducts);
    }

    @PostMapping("/toggle")
    public ResponseEntity<Response<String>> toggleFavorite(@RequestBody Favorite favorite) {
        try {
            boolean isFavorite = favoriteService.toggleFavorite(favorite);
            String message = isFavorite ? "Product added to favorites" : "Product removed from favorites";
            return Response.successfulResponse(message, null);
        } catch (DataNotFoundException ex) {
            return Response.failedResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        } catch (Exception ex) {
            return Response.failedResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred");
        }
    }

}
