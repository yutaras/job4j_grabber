package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LoadPost {
    /*public Post loadPost(String page) throws IOException {
        Document doc = Jsoup.connect(page).get();
        Elements row = doc.select(".msgBody");
        Element description = row.get(0).parent().child(1);
        return new Post()
    }*/

    public static void main(String[] args) throws IOException {
        String page =
                "https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t";
        Document doc = Jsoup.connect(page).get();
        Elements row = doc.select(".msgBody");
        Element description = row.get(0).parent().child(1);
        Elements lines = doc.select(".msgFooter");
        Element created = lines.get(0).parent();
        String[] date = created.text().split("    ");
        System.out.println(description.text());
        System.out.println(date[0]);
    }
}
