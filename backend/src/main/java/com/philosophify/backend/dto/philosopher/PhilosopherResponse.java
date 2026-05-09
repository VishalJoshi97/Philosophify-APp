package com.philosophify.backend.dto.philosopher;

import com.philosophify.backend.dto.quote.QuoteResponse;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhilosopherResponse {

    private Long id;
    private String name;
    private String imageUrl;
    private String shortBio;
    private String fullBio;
    private List<QuoteResponse> quotes;//[text,..]
}