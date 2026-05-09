package com.philosophify.backend.controller;

import com.philosophify.backend.dto.auth.request.LoginRequest;
import com.philosophify.backend.dto.auth.request.RegisterRequest;
import com.philosophify.backend.dto.auth.response.JwtResponse;
import com.philosophify.backend.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest req){

    boolean isUsername=authService.existsByUsername(req);
    boolean isEmail=authService.existsByEmail(req);

   if(isUsername) return ResponseEntity.badRequest().body("User Already Exists!");

   if ((isEmail)) return ResponseEntity.badRequest().body("Email Already in Use!");

   authService.register(req);

   return ResponseEntity.ok("User registered successfully!");
    }


    //LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

      JwtResponse jwtResponse=authService.login(loginRequest);

        return ResponseEntity.ok(jwtResponse);
    }

   
    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createAdmin(@RequestBody RegisterRequest req) {

        boolean isUsername=authService.existsByUsername(req);
        boolean isEmail=authService.existsByEmail(req);

        if(isUsername) return ResponseEntity.badRequest().body("User Already Exists!");

        if ((isEmail)) return ResponseEntity.badRequest().body("Email Already in Use!");
        authService.createAdmin(req);

        return ResponseEntity.ok("Admin created successfully!");
    }


    //OAuth2 Login
    @GetMapping("/oauth-profile")
    public Map<String,Object> user(
            @AuthenticationPrincipal OAuth2User principal
    ){
        return principal.getAttributes();
    }
}