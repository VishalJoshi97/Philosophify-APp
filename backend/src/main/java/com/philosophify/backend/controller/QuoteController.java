package com.philosophify.backend.controller;

import com.philosophify.backend.dto.quote.QuoteResponse;
import com.philosophify.backend.service.quote.QuoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final QuoteService service;

    public QuoteController(QuoteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public QuoteResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }
}