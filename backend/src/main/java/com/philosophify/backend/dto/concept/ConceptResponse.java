package com.philosophify.backend.dto.concept;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConceptResponse {

    private Long id;
    private String title;
    private String category;
    private String explanation;
    private String example;
}