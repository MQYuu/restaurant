package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {
    @GetMapping("/dashboard/system")
    public String systemDashboard(Model model) {
        return "layouts/system/system-dashboard";
    }
}
