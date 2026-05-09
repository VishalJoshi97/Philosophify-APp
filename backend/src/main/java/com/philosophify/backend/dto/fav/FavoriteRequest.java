package com.philosophify.backend.dto.fav;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteRequest {

    private Long userId;
    private String type;
    private Long referenceId;
}