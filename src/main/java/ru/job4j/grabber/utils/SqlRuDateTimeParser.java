package ru.job4j.grabber.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

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
        String[] twoParts = parse.split(",");
        LocalTime time = LocalTime.parse(twoParts[1].replace(" ", ""), dtf);
        String[] partsOfDate = twoParts[0].split(" ");
        LocalDate date = LocalDate.of(2000 + Integer.parseInt(partsOfDate[2]),
                Integer.parseInt(MONTHS.get(partsOfDate[1])),
                Integer.parseInt(partsOfDate[0]));
        LocalDateTime ldt = LocalDateTime.of(date, time);
        System.out.println(ldt);
        return ldt;
    }

    public static void main(String[] args) {
        String data = "2 дек 22, 10:50";
        SqlRuDateTimeParser sql = new SqlRuDateTimeParser();
        sql.parse(data);
    }
}
