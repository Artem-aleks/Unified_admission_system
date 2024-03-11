package com.university.university.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class SingUpController{
    @GetMapping("/Sing")
    public String Sing(Model model) {
        return "Sing";
    }

}