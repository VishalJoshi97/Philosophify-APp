package com.philosophify.backend.repository;



import com.philosophify.backend.model.Concept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConceptRepository extends JpaRepository<Concept, Long> {}
