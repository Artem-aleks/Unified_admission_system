package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.University;
import com.example.registrationlogindemo.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UniversityController {

    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping("/universities")
    public String getUniversities(Model model) {
        List<University> universities = universityRepository.findAll();
        model.addAttribute("universities", universities);
        return "universities";
    }
}
