package com.example.registrationlogindemo.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MireaParserImpl implements ApplicationRunner {

    private static String pageTitle;
    private static List<String> headings = new ArrayList<>();
    private static List<String> text = new ArrayList<>();
    private static List<String> imageUrls = new ArrayList<>();

    @Override
    public void run(ApplicationArguments args) {
        parseUrl("https://www.mirea.ru/about/history-of-the-university/history-of-the-university/");
    }

    private void parseUrl(String url) {
        try {
            // Получаем HTML-документ страницы
            Document doc = Jsoup.connect(url).get();

            // Проверяем, что HTML-документ был получен успешно
            if (doc == null) {
                System.out.println("Не удалось получить HTML-документ страницы");
                return;
            }

            // Извлекаем заголовок страницы
            pageTitle = doc.title();
            System.out.println("Заголовок страницы: " + pageTitle);

            // Извлекаем заголовок и текст
            Elements headingElements = doc.select("h1, h2, h3, h4, h5, h6");
            Elements textElements = doc.select("p");

            // Извлекаем изображения
            Elements imageElements = doc.select("img");

            // Заполняем списки
            for (Element headingElement : headingElements) {
                headings.add(headingElement.text());
            }

            for (Element textElement : textElements) {
                text.add(textElement.text());
            }

            for (Element imageElement : imageElements) {
                imageUrls.add(imageElement.absUrl("src"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public List<String> getHeadings() {
        return headings;
    }

    public List<String> getText() {
        return text;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }
}
