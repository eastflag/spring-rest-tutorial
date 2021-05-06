package com.eastflag.fullstack.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginVO {
    private String jwt;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
