package com.philosophify.backend.repository;

import com.philosophify.backend.model.Philosopher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PhilosopherRepository extends JpaRepository<Philosopher, Long> {
    @Query("SELECT p FROM Philosopher p LEFT JOIN FETCH p.quotes")
    List<Philosopher> findAllWithQuotes();

    Optional<Philosopher> findByName(Long id);
}

