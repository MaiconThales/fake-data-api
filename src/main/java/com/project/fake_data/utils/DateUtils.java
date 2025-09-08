package com.project.fake_data.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

public class DateUtils {

    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDate randomDate() {
        LocalDate start = LocalDate.of(1950, 1, 1);
        LocalDate end = LocalDate.now();

        long days = ChronoUnit.DAYS.between(start, end);
        long randomDay = ThreadLocalRandom.current().nextLong(days + 1);

        return start.plusDays(randomDay);
    }

    public static LocalDateTime randomDateTime() {
        LocalDateTime start = LocalDateTime.of(1950, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.now();

        long seconds = ChronoUnit.SECONDS.between(start, end);
        long randomSecond = ThreadLocalRandom.current().nextLong(seconds + 1);

        return start.plusSeconds(randomSecond);
    }

}