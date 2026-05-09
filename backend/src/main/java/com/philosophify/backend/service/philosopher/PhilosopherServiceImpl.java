package com.philosophify.backend.service.philosopher;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.philosophify.backend.dto.admin.AddQuoteRequest;
import com.philosophify.backend.dto.philosopher.CreatePhilosopherRequest;
import com.philosophify.backend.dto.philosopher.PhilosopherMapper;
import com.philosophify.backend.dto.philosopher.PhilosopherResponse;
import com.philosophify.backend.exception.UserNotFoundException;
import com.philosophify.backend.model.Philosopher;
import com.philosophify.backend.model.Quote;
import com.philosophify.backend.repository.PhilosopherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PhilosopherServiceImpl implements PhilosopherService {

    private final PhilosopherRepository philosopherRepository;
    private final Cloudinary cloudinary;

    //admin
    //create philosophers
    @Override
    public void create(CreatePhilosopherRequest request) {

        Philosopher p = new Philosopher();
        p.setName(request.getName());
        p.setImageUrl(request.getImageUrl());
        p.setShortBio(request.getShortBio());
        p.setFullBio(request.getFullBio());

        philosopherRepository.save(p);
    }


    //get all philosophers
    @Override
    public List<PhilosopherResponse> getAll() {
        return philosopherRepository.findAllWithQuotes()
                .stream()
                .map(PhilosopherMapper::toResponse)
                .toList();
    }

    //get a single philosopher
    @Override
    public PhilosopherResponse getById(Long id) {
        Philosopher p = philosopherRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Philosopher id "+id+" not found"));

        return PhilosopherMapper.toResponse(p);
    }

    //admin only
    //create quotes
    //and see in Quotes table
    @Override
    public void addQuote(Long philosopherId, AddQuoteRequest request) {

        Philosopher philosopher = philosopherRepository.findById(philosopherId)
                .orElseThrow(() -> new RuntimeException("Philosopher not found"));

        Quote quote = new Quote();
        quote.setText(request.getText());
        quote.setPhilosopher(philosopher);//id of philosopher

        // Add to list
        philosopher.getQuotes().add(quote);

        philosopherRepository.save(philosopher);
    }



    //admin
    //update image url
    @Override
    public String updatePhilosopherImage(Long id, MultipartFile file) {

        try {
            // 1. Find philosopher
            Philosopher philosopher = philosopherRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Philosopher not found"));



            // 3. Upload new image
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("folder", "philosophers")
            );

            String imageUrl = uploadResult.get("secure_url").toString();
            String publicId = uploadResult.get("public_id").toString();

            // 4. Update DB
            philosopher.setImageUrl(imageUrl);

            philosopherRepository.save(philosopher);

            return imageUrl;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Image update failed");
        }
    }

    //upload image in bulk
    public void uploadBulkImages(List<Long> ids, List<MultipartFile> files) throws IOException {

        if (ids.size() != files.size()) {
            throw new RuntimeException("IDs and files count must match");
        }

        for (int i = 0; i < ids.size(); i++) {
            Long id = ids.get(i);
            MultipartFile file = files.get(i);

            Philosopher philosopher = philosopherRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Not found"));

            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );

            String imageUrl = (String) uploadResult.get("secure_url");

            philosopher.setImageUrl(imageUrl);
            philosopherRepository.save(philosopher);
        }
    }}