package com.philosophify.backend.dto.auth.request;

import lombok.Data;

@Data//->get ,set,rac
public class RegisterRequest {

    private String username;

    private String email;

    private String password;

//    private Role role;
}
