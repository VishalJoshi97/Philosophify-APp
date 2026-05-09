package com.philosophify.backend.service.quote;

import com.philosophify.backend.dto.quote.QuoteResponse;
import org.springframework.stereotype.Service;

@Service
public interface QuoteService {
    //already saved by philosopher
    QuoteResponse getById(Long id);
}
