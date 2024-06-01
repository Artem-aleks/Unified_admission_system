package com.example.registrationlogindemo.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaumankaParserImpl {
    private static String title;
    private static String content;
    private static List<String> imageUrls = new ArrayList<>();

    public static void parseWebpage(String url) throws IOException {
        Document document = Jsoup.connect(url).get();

        // Извлечение заголовка
        title = document.title();
        System.out.println("Title: " + title);

        // Извлечение текста статьи
        StringBuilder contentBuilder = new StringBuilder();
        Elements paragraphElements = document.select("div.contentBlock");
        for (Element paragraph : paragraphElements) {
            contentBuilder.append(paragraph.text()).append("\n");
        }
        content = contentBuilder.toString().trim();
        System.out.println("Content: " + content);

        // Извлечение изображений
        Elements imageElements = document.select(".entry-content img");
        for (Element imageElement : imageElements) {
            String imageUrl = imageElement.attr("src");
            imageUrls.add(imageUrl);
            System.out.println("Image URL: " + imageUrl);
        }
    }

    public static String getTitle() {
        return title;
    }

    public static String getContent() {
        return content;
    }

    public static List<String> getImageUrls() {
        return imageUrls;
    }
}
