package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.ListUniversity;
import com.example.registrationlogindemo.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class UniversityController {

    @Autowired
    private UniversityRepository listUniversitiesRepository;

    @GetMapping("/university")
    public String University(Model model) {
        Iterable<ListUniversity> listUniversities = listUniversitiesRepository.findAll();
        model.addAttribute("listUniversities", listUniversities);
        return "university";
    }

}
