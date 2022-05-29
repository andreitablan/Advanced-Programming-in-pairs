package com;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

public class Info {

    public Info(Locale locale) {

        Locale defaultLocale = null;
        LocalDateTime localDateTime = LocalDateTime.now();
        String[] weekdays = new DateFormatSymbols().getWeekdays();
        String[] months = new DateFormatSymbols().getMonths();
        Currency currentCurrency = Currency.getInstance(locale);

        System.out.println("Country: " + locale.getCountry());
        System.out.println("Language: " + locale.getDisplayLanguage());
        System.out.println("Currency: " + currentCurrency);
        System.out.println("Week Days: " + Arrays.toString(weekdays));
        System.out.println("Months " + Arrays.toString(months));
        System.out.println("Today: " + localDateTime);

    }
}
