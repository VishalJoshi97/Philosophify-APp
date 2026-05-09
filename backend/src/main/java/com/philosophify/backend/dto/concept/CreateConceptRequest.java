package com.philosophify.backend.dto.concept;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateConceptRequest {
    private String title;
    private String category;
    private String explanation;
    private String example;
}
