package com.philosophify.backend.dto.fav;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteResponse {
    private Long id;
    private String type;
    private Object data;//philosopher  or quote response
    private String author;
}
