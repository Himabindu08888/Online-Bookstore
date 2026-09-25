package com.bookstore.app.controller;

import com.bookstore.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String fullName,
                            @RequestParam String email,
                            @RequestParam String password,
                            Model model) {
        try {
            userService.register(fullName, email, password);
            model.addAttribute("success", "Account created successfully. Please log in.");
            return "login";
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
