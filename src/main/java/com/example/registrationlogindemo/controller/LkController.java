package com.example.registrationlogindemo.controller;


import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LkController {

    @Autowired
    private UserService userService;

    @GetMapping("/Lk")
    public String showUserInfo(Model model) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Fetch the user details from the database
        User user = userService.findByEmail(email);

        // Add the user's email and name to the model
        model.addAttribute("email", user.getEmail());
        model.addAttribute("name", user.getName());

        return "Lk";
    }
}