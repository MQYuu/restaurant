package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/dashboard/admin")
    public String adminDashboard(Model model) {
        return "layouts/admin/admin-dashboard";
    }
}
