package com.university.university.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class LKController {
    @GetMapping("/LkMain")
    public String LKMain(Model model) {
        return "LkMain";
    }

}
