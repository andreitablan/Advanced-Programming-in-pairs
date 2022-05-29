package app;

import com.DisplayLocales;
import com.Info;
import com.SetLocale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import static java.lang.System.exit;

public class LocaleExplore {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Give a command:");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String command = null;
            String[] commands = new String[2];
            try {
                command = bufferedReader.readLine();
                commands = command.split(" ");

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (commands[0].equals("locales")) {
                DisplayLocales displayLocales = new DisplayLocales();
            } else if (commands[0].equals("set")) {
                String[] arguments;
                arguments = commands[1].split("_");
                if (arguments.length == 1) {
                    Locale locale = new Locale(arguments[0]);
                    SetLocale setLocale = new SetLocale(locale);
                } else if (arguments.length == 2) {
                    Locale locale = new Locale(arguments[0], arguments[1]);
                    SetLocale setLocale = new SetLocale(locale);
                } else if (arguments.length == 3) {
                    Locale locale = new Locale(arguments[0], arguments[1], arguments[2]);
                    SetLocale setLocale = new SetLocale(locale);
                }
                System.out.println("We did set locale to " + commands[1] + ".");
            } else if (commands[0].equals("info")) {
                Info info = new Info(Locale.getDefault());
            } else if (commands[0].equals("exit")) {
                exit(0);
            } else {
                System.out.println();
            }
        }
    }
}

