package com.philosophify.backend.service.philosopher;

import com.philosophify.backend.dto.admin.AddQuoteRequest;
import com.philosophify.backend.dto.philosopher.CreatePhilosopherRequest;
import com.philosophify.backend.dto.philosopher.PhilosopherResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhilosopherService {
    //create a single philosopher by admin
    void create(CreatePhilosopherRequest request);

    //get all philosophers for logged-in users
    List<PhilosopherResponse> getAll();

    //get a single philosopher by id
    PhilosopherResponse getById(Long id);

    //add quote wrt a philosopher by admin
    public void addQuote(Long philosopherId, AddQuoteRequest request);

    //upload image url to Cloudinary
    public String updatePhilosopherImage(Long id, MultipartFile file);

    //upload in bulk
    public void uploadBulkImages(List<Long> ids, List<MultipartFile> files) throws IOException;
}