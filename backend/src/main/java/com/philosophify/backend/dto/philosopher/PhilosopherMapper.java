package com.philosophify.backend.dto.philosopher;

import com.philosophify.backend.dto.quote.QuoteResponse;
import com.philosophify.backend.model.Philosopher;

import java.util.List;

public class PhilosopherMapper {

    public static PhilosopherResponse toResponse(Philosopher p) {

        PhilosopherResponse res = new PhilosopherResponse();
        res.setId(p.getId());
        res.setName(p.getName());
        res.setImageUrl(p.getImageUrl());
        res.setShortBio(p.getShortBio());
        res.setFullBio(p.getFullBio());

        List<QuoteResponse> quotes = p.getQuotes()
                .stream()
                .map(q -> {
                    QuoteResponse qr = new QuoteResponse();
                    qr.setId(q.getId());
                    qr.setText(q.getText());
                    return qr;
                })
                .toList();

        res.setQuotes(quotes);

        return res;
    }
}