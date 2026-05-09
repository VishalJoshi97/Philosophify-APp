package com.philosophify.backend.service.fav;

import com.philosophify.backend.dto.fav.FavoriteRequest;
import com.philosophify.backend.dto.fav.FavoriteResponse;
import com.philosophify.backend.model.Favorite;

import java.util.List;

public interface FavoriteService {
    //add to fav by user
    void addFavorite(FavoriteRequest request);

    //get all fav of use
    List<FavoriteResponse> getUserFavorites(Long userId);

    //remove from fav
    public void removeFavorite(Long id);
}