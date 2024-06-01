package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.University;
import com.example.registrationlogindemo.repository.UniversityRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UniversityParser {
    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityParser(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public void parseAndSaveUniversities(String url) {
        List<String> universities = parseUniversities(url);
        for (String university : universities) {
            int randomScore = generateRandomScore();
            University entity = new University(university, randomScore);
            universityRepository.save(entity);
        }
    }

    private List<String> parseUniversities(String url) {
        List<String> universities = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements universityElements = document.select("b.search-uni-name");
            for (Element universityElement : universityElements) {
                universities.add(universityElement.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return universities;
    }

    private int generateRandomScore() {
        Random random = new Random();
        return random.nextInt(54) + 250; // Диапазон от 250 до 303
    }
}
