package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.service.impl.MireaParserImpl;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MireaController{

    private final MireaParserImpl mireaParserImpl;

    public MireaController(MireaParserImpl mireaParserImpl) {
        this.mireaParserImpl = mireaParserImpl;
    }

    @GetMapping("/mirea-info")
    public String getHistory(Model model) {
        // Данные уже были загружены при запуске приложения, теперь мы можем их использовать
        model.addAttribute("pageTitle", mireaParserImpl.getPageTitle());
        model.addAttribute("headings", mireaParserImpl.getHeadings());
        model.addAttribute("text", mireaParserImpl.getText());
        model.addAttribute("imageUrls", mireaParserImpl.getImageUrls());

        return "mirea-info";
    }
}

