package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;

public class LoadPost {
    public static Post loadPost(String page) throws IOException {
        Document doc = Jsoup.connect(page).get();
        Elements nameE = doc.select(".forummenu");
        Element name = nameE.get(0).parent().child(3);
        String[] listName = name.text().split("  ");
        Elements row = doc.select(".msgBody");
        Element description = row.get(0).parent().child(1);
        Elements lines = doc.select(".msgFooter");
        Element createdE = lines.get(0).parent();
        String[] date = createdE.text().split("    ");
        SqlRuDateTimeParser sql = new SqlRuDateTimeParser();
        LocalDateTime created = sql.parse(date[0]);
        return new Post(listName[0], page, description.text(), created);
    }

    public static void main(String[] args) throws IOException {
        String page =
                "https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t";
        System.out.println(loadPost(page));
    }
}
