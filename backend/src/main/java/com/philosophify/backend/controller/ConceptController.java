package com.philosophify.backend.controller;

import com.philosophify.backend.dto.concept.ConceptResponse;
import com.philosophify.backend.dto.concept.CreateConceptRequest;
import com.philosophify.backend.service.concept.ConceptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concepts")
@RequiredArgsConstructor
public class ConceptController {

    private final ConceptService conceptService;

    //admin
    //create concepts
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateConceptRequest request) {
        conceptService.create(request);
        return ResponseEntity.ok("Concept created!");
    }

    //create in bulk
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/bulk")
    public ResponseEntity<String> createBulk(@RequestBody List<CreateConceptRequest> requests) {
        conceptService.createBulk(requests);
        return ResponseEntity.ok("Concepts created!");
    }

    //get all concepts
    @GetMapping
    public List<ConceptResponse> getAll() {
        return conceptService.getAllConcepts();
    }
}