package com.eastflag.fullstack.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {
    private Long id;
    private String email;
    private String password;
    private String username;
    private List<String> roles;

    private String jwt;
}
