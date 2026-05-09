package com.philosophify.backend.service.concept;

import com.philosophify.backend.dto.concept.ConceptResponse;
import com.philosophify.backend.dto.concept.CreateConceptRequest;

import java.util.List;

public interface ConceptService {

    //admin
    //create concept
    void create(CreateConceptRequest request);

    //create in bulk
    public void createBulk(List<CreateConceptRequest> requests);

    //get concepts by all users
    List<ConceptResponse> getAllConcepts();
}