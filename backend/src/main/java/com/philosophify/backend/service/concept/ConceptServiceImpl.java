package com.philosophify.backend.service.concept;

import com.philosophify.backend.dto.concept.ConceptResponse;
import com.philosophify.backend.dto.concept.CreateConceptRequest;
import com.philosophify.backend.model.Concept;
import com.philosophify.backend.repository.ConceptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConceptServiceImpl implements ConceptService {

    private final ConceptRepository conceptRepository;


    //admin
    //create concepts
    @Override
    public void create(CreateConceptRequest request) {
        Concept c = new Concept();
        c.setTitle(request.getTitle());
        c.setCategory(request.getCategory());
        c.setExplanation(request.getExplanation());
        c.setExample(request.getExample());

        conceptRepository.save(c);
    }

    //create in bulk
    public void createBulk(List<CreateConceptRequest> requests) {
        List<Concept> concepts = requests.stream()
                .map(req -> new Concept(
                        req.getTitle(),
                        req.getCategory(),
                        req.getExplanation(),
                        req.getExample()
                ))
                .toList();

        conceptRepository.saveAll(concepts);
    }


    //get all concepts
    @Override
    public List<ConceptResponse> getAllConcepts() {
        return conceptRepository.findAll()
                .stream()
                .map(c -> {
                    ConceptResponse res = new ConceptResponse();
                    res.setId(c.getId());
                    res.setTitle(c.getTitle());
                    res.setCategory(c.getCategory());
                    res.setExplanation(c.getExplanation());
                    res.setExample(c.getExample());
                    return res;
                })
                .toList();
    }
}