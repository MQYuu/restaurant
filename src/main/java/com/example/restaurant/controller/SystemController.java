package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {
    @GetMapping("/system")
    public String systemDashboard() {
        return "layouts/system/dashboard";
    }
}

