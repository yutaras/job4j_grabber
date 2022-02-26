package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {

    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public Post detail(String link) {
        String name = null;
        String description = null;
        LocalDateTime created = null;
        try {
            Document doc = Jsoup.connect(link).get();
            name = doc.select(".messageHeader").get(0).ownText();
            description = doc.select(".msgBody").get(1).text();
            Element createdE = doc.select(".msgFooter").get(0);
            String[] date = createdE.text().split("    ");
            created = dateTimeParser.parse(date[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Post(name, link, description, created);
    }

    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(link).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                if (href.text().toLowerCase().contains("java")
                        && !href.text().toLowerCase().contains("javascript")) {
                    list.add(detail(href.attr("href")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        DateTimeParser parser = new SqlRuDateTimeParser();
        Parse pars = new SqlRuParse(parser);
        for (int i = 1; i < 6; i++) {
            String link = "https://www.sql.ru/forum/job-offers/";
            System.out.println(pars.list(link));
        }
    }
}