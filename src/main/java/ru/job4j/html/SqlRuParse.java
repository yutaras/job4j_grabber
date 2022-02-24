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

    @Override
    public Post detail(String link) throws IOException {
        SqlRuDateTimeParser sql = new SqlRuDateTimeParser();
        Document doc = Jsoup.connect(link).get();
        String name = doc.select(".messageHeader").get(0).ownText();
        String description = doc.select(".msgBody").get(1).text();
        Element createdE = doc.select(".msgFooter").get(0);
        String[] date = createdE.text().split("    ");
        LocalDateTime created = sql.parse(date[0]);
        return new Post(name, link, description, created);
    }

    @Override
    public List<Post> list(String link) throws IOException {
        List<Post> list = new ArrayList<>();
        Document doc = Jsoup.connect(link).get();
        Elements row = doc.select(".postslisttopic");
        for (Element td : row) {
            Element href = td.child(0);
            list.add(detail(href.attr("href")));
        }
        return list;
    }


    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            String link = "https://www.sql.ru/forum/job-offers/";


        }
    }

}