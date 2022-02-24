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
        SqlRuDateTimeParser sql = new SqlRuDateTimeParser();
        Document doc = Jsoup.connect(page).get();
        String name = doc.select(".messageHeader").get(0).ownText();
        String description = doc.select(".msgBody").get(1).text();
        Element createdE = doc.select(".msgFooter").get(0);
        String[] date = createdE.text().split("    ");
        LocalDateTime created = sql.parse(date[0]);
        return new Post(name, page, description, created);
    }

    public static void main(String[] args) throws IOException {
        String page =
                "https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t";
        System.out.println(loadPost(page));
    }
}
