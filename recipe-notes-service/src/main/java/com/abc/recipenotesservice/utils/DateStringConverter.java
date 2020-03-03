package com.abc.recipenotesservice.utils;

import java.time.format.DateTimeFormatter;


public class DateStringConverter {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:MM:SS");

    private DateStringConverter(){
        throw new RuntimeException("Can't instantiate!");
    }
}
