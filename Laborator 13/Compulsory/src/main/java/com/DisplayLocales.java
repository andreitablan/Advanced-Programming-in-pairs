package com;

import java.text.DateFormat;
import java.util.Locale;

public class DisplayLocales {
    public DisplayLocales(){
        Locale[] locales= DateFormat.getAvailableLocales();
        for (Locale locale : locales) {
            System.out.println(locale.toString());
        }
    }
}
