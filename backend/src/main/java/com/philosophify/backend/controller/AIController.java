package com.philosophify.backend.controller;


import com.philosophify.backend.service.ai.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin
public class AIController {

    @Autowired
    private OllamaService ollamaService;

    @PostMapping("/think")
    public String think(@RequestBody String input) {
        return ollamaService.generateResponse(input);
    }
}
