package com.philosophify.backend.controller;

import com.philosophify.backend.dto.admin.AddQuoteRequest;
import com.philosophify.backend.dto.philosopher.CreatePhilosopherRequest;
import com.philosophify.backend.dto.philosopher.PhilosopherResponse;
import com.philosophify.backend.service.philosopher.PhilosopherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/philosophers")
@RequiredArgsConstructor
public class PhilosopherController {

    private final PhilosopherService philosopherService;

    //admin
    //create philosophers
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreatePhilosopherRequest request) {
        philosopherService.create(request);
        return ResponseEntity.ok("Philosopher created");
    }

    //get all philosophers
    @GetMapping
    public List<PhilosopherResponse> getAll() {
        return philosopherService.getAll();
    }


    //get a single philosopher
    @GetMapping("/{id}")
    public PhilosopherResponse getById(@PathVariable Long id) {
        return philosopherService.getById(id);
    }

    //admin
    //create quotes per philosopher
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/quotes")
    public ResponseEntity<String> addQuote(
            @PathVariable Long id,
            @RequestBody AddQuoteRequest request
    ) {
        philosopherService.addQuote(id, request);

        return ResponseEntity.ok("Quote Created!");
    }

    //update image url
    @PutMapping("/{id}/image")
    public String updateImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        return philosopherService.updatePhilosopherImage(id, file);
    }

    //upload in bulk
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/bulk-images")
    public ResponseEntity<String> uploadBulkImages(
            @RequestParam("ids") List<Long> ids,
            @RequestParam("files") List<MultipartFile> files) throws IOException {

        philosopherService.uploadBulkImages(ids, files);
        return ResponseEntity.ok("Images uploaded successfully!");
    }
}
