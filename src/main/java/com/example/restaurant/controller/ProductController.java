package com.example.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.restaurant.entity.Product;
import com.example.restaurant.service.ProductService;

import org.springframework.ui.Model;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/listProduct")
    public String showProductPage(Model model) {
        // Lấy danh sách sản phẩm từ service
        List<Product> products = productService.getAllProducts();

        // Thêm danh sách sản phẩm vào model
        model.addAttribute("products", products);

        return "layouts/product";
    }
}

