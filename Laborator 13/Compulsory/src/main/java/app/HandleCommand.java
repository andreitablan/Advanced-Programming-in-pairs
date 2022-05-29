package app;

import com.DisplayLocales;
import com.Info;
import com.SetLocale;

import java.util.Locale;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class HandleCommand {
    public HandleCommand() {
    }

    public void handle(String[] commands, ResourceBundle messages) {

        if (commands[0].equals("locales")) {
             new DisplayLocales();
        } else if (commands[0].equals("set")) {
            String[] arguments;
            arguments = commands[1].split("_");
            if (arguments.length == 1) {
                Locale locale = new Locale(arguments[0]);
                new SetLocale(locale, messages);
            } else if (arguments.length == 2) {
                Locale locale = new Locale(arguments[0], arguments[1]);
                new SetLocale(locale, messages);
            } else if (arguments.length == 3) {
                Locale locale = new Locale(arguments[0], arguments[1], arguments[2]);
                new SetLocale(locale, messages);
            }
        } else if (commands[0].equals("info")) {
            Info info = new Info(Locale.getDefault(), messages);
        } else if (commands[0].equals("exit")) {
            exit(0);
        } else {
            System.out.println(messages.getString("invalid"));
        }
    }
}
