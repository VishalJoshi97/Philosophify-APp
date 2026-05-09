package com.philosophify.backend.dto.quote;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteResponse {

    private Long id;
    private String text;
}