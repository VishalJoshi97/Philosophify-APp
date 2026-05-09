package com.philosophify.backend.repository;

import com.philosophify.backend.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

}
