package com.example.registrationlogindemo.controller;


import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.UserService;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userService.findByEmail(email);

        model.addAttribute("profile_image", "/img/mirera.jpeg");
        model.addAttribute("email", user.getEmail());
        model.addAttribute("name", user.getName());
        model.addAttribute("ege_scored_subjects", user.getEgeScoredSubjects());
        model.addAttribute("total_ege_score", user.getTotalEgeScore());
        model.addAttribute("universities_applying_to", user.getUniversitiesApplyingTo());


        return "Lk";
    }
}