package com.philosophify.backend.service.ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OllamaService {

    private final String URL = "http://localhost:11434/api/generate";

    public String generateResponse(String userInput) {

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        String prompt = """
        You are a philosophical companion.
                
        Rules:
        - Give 1 short insight (1 line)
        - Then ask 1 sharp question
        - Max 30 words
        -Be direct and clear

        User: %s
        """.formatted(userInput);

        try {
            // ✅ Build JSON safely
            Map<String, Object> body = new HashMap<>();
            body.put("model", "mistral");
            body.put("prompt", prompt);
            body.put("stream", false);

            // Optional: limit output
            Map<String, Object> options = new HashMap<>();
            options.put("num_predict", 100);
            body.put("options", options);

            String json = mapper.writeValueAsString(body);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(json, headers);

            ResponseEntity<String> response =
                    restTemplate.postForEntity(URL, request, String.class);

            return extractResponse(response.getBody());

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String extractResponse(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(json, Map.class);
            return map.get("response").toString();
        } catch (Exception e) {
            return "Error parsing response";
        }
    }
}