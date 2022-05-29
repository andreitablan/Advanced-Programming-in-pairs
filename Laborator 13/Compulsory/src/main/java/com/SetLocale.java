package com;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    public SetLocale(Locale locale, ResourceBundle messages) {
        Locale.setDefault(locale);
        messages = ResourceBundle.getBundle("res/Messages", locale);
        String pattern=messages.getString("locale.set");
        Object [] arguments={Locale.getDefault()};
        String localeSet = new MessageFormat(pattern).format(arguments);
        System.out.println(localeSet);
    }
}
