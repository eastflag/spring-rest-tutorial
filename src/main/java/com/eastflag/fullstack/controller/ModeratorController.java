package com.eastflag.fullstack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moderator")
public class ModeratorController {
    @GetMapping("/dashboard")
    public String getDashboard() {
        return "success";
    }
}
