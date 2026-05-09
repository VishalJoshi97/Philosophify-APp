package com.philosophify.backend.model;

import com.philosophify.backend.enums.Role;
import com.philosophify.backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAdmin() {

        if (!userRepository.existsByEmail("admin@gmail.com")) {

            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRole(Role.ROLE_ADMIN);

            userRepository.save(admin);

            System.out.println("✅ Admin created: admin@gmail.com / admin123");
        }
    }
}
