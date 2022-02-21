package ru.job4j.grabber.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final String TODAY = "сегодня";
    private static final String YESTERDAY = "вчера";

    private static final Map<String, String> MONTHS = Map.ofEntries(
            Map.entry("янв", "1"),
            Map.entry("фев", "2"),
            Map.entry("мар", "3"),
            Map.entry("апр", "4"),
            Map.entry("май", "5"),
            Map.entry("июн", "6"),
            Map.entry("июл", "7"),
            Map.entry("авг", "8"),
            Map.entry("сен", "9"),
            Map.entry("окт", "10"),
            Map.entry("ноя", "11"),
            Map.entry("дек", "12")
    );

    @Override
    public LocalDateTime parse(String parse) {
        LocalDate date;
        String[] twoParts = parse.split(",");
        LocalTime time = LocalTime.parse(twoParts[1].replace(" ", ""),
                FORMATTER);
        String[] partsOfDate = twoParts[0].split(" ");
        if (TODAY.equals(twoParts[0])) {
            date = LocalDate.now();
        } else if (YESTERDAY.equals(twoParts[0])) {
            date = LocalDate.now().minusDays(1);
        } else {
            date = LocalDate.of(2000 + Integer.parseInt(partsOfDate[2]),
                    Integer.parseInt(MONTHS.get(partsOfDate[1])),
                    Integer.parseInt(partsOfDate[0]));
        }
        return LocalDateTime.of(date, time);
    }

    public static void main(String[] args) throws Exception {
        SqlRuDateTimeParser sql = new SqlRuDateTimeParser();
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".postslisttopic");
        for (Element td : row) {
            Element time = td.parent().child(5);
            System.out.println(sql.parse(time.text()));
        }
    }
}
