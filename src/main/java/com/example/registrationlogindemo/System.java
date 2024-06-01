package com.example.registrationlogindemo;


import com.example.registrationlogindemo.repository.UniversityRepository;
import com.example.registrationlogindemo.service.impl.UniversityParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class System {

    public static void main(String[] args) {
        SpringApplication.run(System.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UniversityRepository universityRepository, UniversityParser universityParser) {
        return args -> {
            String url = "https://postupi.info/city/1";
            universityParser.parseAndSaveUniversities(url);


        };
    }
}