package com.example.registrationlogindemo.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MguParserImpl {
    private static String firstHalfContent;

    public static void main(String[] args) {
        String url = "https://ru.wikipedia.org/wiki/Московский_государственный_университет";

        try {
            // Подключаемся к сайту
            Document document = Jsoup.connect(url).get();

            // Получаем первую половину содержимого страницы
            Element firstHalf = getFirstHalf(document.body());

            // Сохраняем содержимое первой половины
            firstHalfContent = firstHalf.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Element getFirstHalf(Element body) {
        int middleIndex = body.children().size() / 2;
        Element firstHalf = new Element("div");

        int counter = 0;
        for (Element child : body.children()) {
            if (counter < middleIndex) {
                firstHalf.appendChild(child);
            } else {
                break;
            }
            counter++;
        }

        return firstHalf;
    }

    public static String getFirstHalfContent() {
        return firstHalfContent;
    }
}
