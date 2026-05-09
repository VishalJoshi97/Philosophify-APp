package com.philosophify.backend.dto.philosopher;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePhilosopherRequest {
    private String name;
    private String imageUrl;
    private String shortBio;
    private String fullBio;
}
