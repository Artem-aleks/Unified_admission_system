package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.service.impl.MguParserImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MguController {

    @GetMapping("/mgu-info")
    public String parseMgu(Model model) {
        MguParserImpl.main(null);
        String firstHalf = MguParserImpl.getFirstHalfContent();
        model.addAttribute("firstHalf", firstHalf);
        return "mgu-info";
    }
}

