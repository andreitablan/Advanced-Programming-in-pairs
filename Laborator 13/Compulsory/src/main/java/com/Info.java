package com;

import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {

    public Info(Locale locale, ResourceBundle messages) {

        Locale defaultLocale = null;
        LocalDateTime localDateTime = LocalDateTime.now();
        String[] weekdays = new DateFormatSymbols().getWeekdays();
        String[] months = new DateFormatSymbols().getMonths();
        Currency currentCurrency = Currency.getInstance(locale);

        String pattern=messages.getString("info");
        Object [] arguments={Locale.getDefault()};
        String information = new MessageFormat(pattern).format(arguments);
        System.out.println(information);

        System.out.println("Country: " + locale.getCountry());
        System.out.println("Language: " + locale.getDisplayLanguage());
        System.out.println("Currency: " + currentCurrency);
        System.out.println("Week Days: " + Arrays.toString(weekdays));
        System.out.println("Months " + Arrays.toString(months));
        System.out.println("Today: " + localDateTime);

    }
}
