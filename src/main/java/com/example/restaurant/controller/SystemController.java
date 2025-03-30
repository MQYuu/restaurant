package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.restaurant.entity.User;
import com.example.restaurant.service.UserService;
import com.example.restaurant.service.AuthorityService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/dashboard/system") // Các đường dẫn thuộc giao diện System
public class SystemController {

    private final UserService userService;
    private final AuthorityService authorityService;

    public SystemController(UserService userService, AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    // Trang Dashboard system 
    @GetMapping("/")
    public String systemDashboard() {
        return "layouts/system/system-dashboard";
    }

    // Danh sách Users
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "layouts/system/users";
    }

    // Form thêm User
    @GetMapping("/users/new")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "layouts/system/add-user";
    }

    // Thêm User
    @PostMapping("/users")
    public String addUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "layouts/system/add-user";
        }
        userService.addUser(user);
        return "redirect:/dashboard/system/users";
    }

    // Form sửa User
    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/dashboard/system/users";
        }
        model.addAttribute("user", user);
        return "layouts/system/edit-user";
    }

    // Cập nhật User
    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute User user) {
        user.setId(id);
        userService.updateUser(id, user);
        return "redirect:/dashboard/system/users";
    }

    // Xóa User
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/dashboard/system/users";
    }

    // Danh sách quyền của User
    @GetMapping("/authorities/{userId}")
    public String userAuthorities(@PathVariable Integer userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("authorities", authorityService.getAuthoritiesByUserId(userId));
        return "layouts/system/authorities";
    }

    // Thêm quyền cho User
    @PostMapping("/authorities")
    public String addAuthority(@RequestParam Integer userId, @RequestParam String
    authority) {
    authorityService.addAuthority(userId, authority);
    return "redirect:/dashboard/system/authorities/" + userId;
    }

    // Xóa quyền của User
    @GetMapping("/authorities/delete/{userId}/{authorityId}")
    public String deleteAuthority(@PathVariable Integer userId, @PathVariable
    Integer authorityId) {
    authorityService.removeAuthorityFromUser(userId, authorityId);
    return "redirect:/dashboard/system/authorities/" + userId;
    }
}
