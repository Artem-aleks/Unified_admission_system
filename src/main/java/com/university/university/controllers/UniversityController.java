package com.university.university.controllers;

import com.university.university.models.ListUniversity;
import com.university.university.repo.ListUniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class UniversityController {

    @Autowired
    private ListUniversityRepository listUniversityRepository;
    @GetMapping("/University")
    public String University(Model model) {
        Iterable<ListUniversity> listUniversity = listUniversityRepository.findAll();
        model.addAttribute("listUniversity", )
        return "University";
    }
}