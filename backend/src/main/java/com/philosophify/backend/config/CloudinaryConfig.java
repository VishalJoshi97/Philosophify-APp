package com.philosophify.backend.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dwjj3xcp6");
        config.put("api_key", "391539277351152");
        config.put("api_secret", "vASzDSYDQFFnxYgNkTAnnggSXjU");
        return new Cloudinary(config);
    }
}