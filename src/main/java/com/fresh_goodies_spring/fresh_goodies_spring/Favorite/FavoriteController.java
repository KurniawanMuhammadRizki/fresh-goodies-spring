package com.fresh_goodies_spring.fresh_goodies_spring.Favorite;

import com.fresh_goodies_spring.fresh_goodies_spring.Favorite.Model.Favorite;
import com.fresh_goodies_spring.fresh_goodies_spring.Favorite.Service.FavoriteService;
import com.fresh_goodies_spring.fresh_goodies_spring.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Favorite>>> getFavorites(){
        List<Favorite> favoriteList = favoriteService.getFavorites();

        if(favoriteList.isEmpty()){
            return Response.successfulResponse("Favorite Empty");
        }
        return Response.successfulResponse("All favorites fetched", favoriteList);
    }

}
