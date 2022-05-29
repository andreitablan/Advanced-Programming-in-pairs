package app;

import com.DisplayLocales;
import com.Info;
import com.SetLocale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class LocaleExplore {
    public static void main(String[] args) throws IOException {
        while (true) {
            SetResourceBundle setResourceBundle=new SetResourceBundle();
            ResourceBundle messages=setResourceBundle.setBundle();

            System.out.println(messages.getString("prompt"));

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String command = null;
            String[] commands = new String[2];
            try {
                command = bufferedReader.readLine();
                commands = command.split(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }

            new HandleCommand().handle(commands,messages);
        }
    }
}

