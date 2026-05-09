package com.philosophify.backend.dto.admin;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddQuoteRequest {
    private String text;
}
