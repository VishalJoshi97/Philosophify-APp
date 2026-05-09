package com.philosophify.backend.controller;

import com.philosophify.backend.dto.fav.FavoriteRequest;
import com.philosophify.backend.dto.fav.FavoriteResponse;
import com.philosophify.backend.service.fav.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService service) {
        this.favoriteService = service;
    }

    //add to fav
    @PostMapping
    public ResponseEntity<String> addFav(@RequestBody FavoriteRequest request) {

        favoriteService.addFavorite(request);
        return ResponseEntity.ok("Added to favourites!");
    }

    //get all fav for a user
    @GetMapping("/{userId}")
    public List<FavoriteResponse> getUserFavorites(@PathVariable Long userId) {
        return favoriteService.getUserFavorites(userId);
    }

    //delete fav
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFavorite(@PathVariable Long id){
        favoriteService.removeFavorite(id);

        return ResponseEntity.ok("Removed from favorites");
    }
}