package com.philosophify.backend.service.quote;

import com.philosophify.backend.dto.quote.QuoteResponse;
import com.philosophify.backend.model.Quote;
import com.philosophify.backend.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;

    //u will get quotes by philosophers
    //so get them by is
    @Override
    public QuoteResponse getById(Long id) {
        Quote q = quoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quote not found"));

        QuoteResponse res = new QuoteResponse();
        res.setId(q.getId());
        res.setText(q.getText());
        return res;
    }
}
