package com.example.registrationlogindemo.controller;


import com.example.registrationlogindemo.service.impl.BaumankaParserImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class BaumankaController {

    @GetMapping("/baumanka-info")
    public String getBaumankaPage(Model model) {
        String url = "https://glavportal.com/materials/ot-remeslennyh-masterskih-do-tehnicheskogo-vuza-nomer-odin-190-let-slavnoj-istorii-mgtu-im-n-e-baumana";
        try {
            BaumankaParserImpl.parseWebpage(url);
            model.addAttribute("title", BaumankaParserImpl.getTitle());
            model.addAttribute("content", BaumankaParserImpl.getContent());
            model.addAttribute("imageUrls", BaumankaParserImpl.getImageUrls());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "baumanka-info";
    }
}
