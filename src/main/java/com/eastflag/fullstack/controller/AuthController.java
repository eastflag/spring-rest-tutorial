package com.eastflag.fullstack.controller;

import com.eastflag.fullstack.domain.ERole;
import com.eastflag.fullstack.domain.LoginVO;
import com.eastflag.fullstack.domain.UserVO;
import com.eastflag.fullstack.persistence.AuthMapper;
import com.eastflag.fullstack.security.JwtUtils;
import com.eastflag.fullstack.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthMapper authMapper;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserVO loginRequest) {
        System.out.println("1: " + loginRequest);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        System.out.println("2: " + authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        System.out.println("3: " + jwt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        System.out.println("4: " + userDetails);

        return ResponseEntity.ok(new LoginVO(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserVO signUpRequest) {
        if (authMapper.existByEmail(signUpRequest.getEmail()) > 0) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        // Create new user's account
        UserVO user = new UserVO();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setUsername(signUpRequest.getUsername());

        authMapper.insertUser(user);

        if (signUpRequest.getRoles() == null || signUpRequest.getRoles().size() == 0) {
            authMapper.insertUserRole(user.getId(), ERole.ROLE_USER.name());
        } else {
            signUpRequest.getRoles().stream()
                    .forEach(role -> authMapper.insertUserRole(user.getId(), role));
        }

        return ResponseEntity.ok("User registered successfully!");
    }
}
