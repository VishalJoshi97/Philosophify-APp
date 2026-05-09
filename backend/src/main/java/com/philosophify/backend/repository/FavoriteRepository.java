package com.philosophify.backend.repository;


import com.philosophify.backend.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    //check for user and ref->philosopher/quote
    boolean existsByUserIdAndTypeAndReferenceId(Long userId, String type, Long referenceId);

    //get fav of user
    List<Favorite> findByUserId(Long userId);
}
