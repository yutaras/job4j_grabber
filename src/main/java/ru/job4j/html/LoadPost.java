package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LoadPost {

    public static void main(String[] args) throws IOException {
        String page =
                "https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t";
        Document doc = Jsoup.connect(page).get();
        Elements row = doc.select(".msgBody");
        for (Element td : row) {
            Element date = td.parent().child(1);
            System.out.println(date.text());
        }
    }
}
