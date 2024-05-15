package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.repository.UniversityRepository;
import com.example.registrationlogindemo.entity.ListUniversity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UniversityParserService {

    @Autowired
    private UniversityRepository universityRepository;

    @Transactional
    public void parseAndSaveData() {
        try {
            Document doc = Jsoup.connect("https://postupi.info/rating/1").get();
            for (Element row : doc.select("table tbody tr")) {
                String universityName = row.select("td:eq(1)").text();
                ListUniversity university = new ListUniversity();
                university.setName(universityName);
                universityRepository.save(university);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}