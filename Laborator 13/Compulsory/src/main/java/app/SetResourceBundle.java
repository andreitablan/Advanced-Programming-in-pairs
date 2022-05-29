package app;

import java.util.Locale;
import java.util.ResourceBundle;

public class SetResourceBundle {
    public SetResourceBundle() {
    }

    public ResourceBundle setBundle(){
        String baseName = "res/Messages";
        Locale locale1 = Locale.getDefault();
        return ResourceBundle.getBundle(baseName, locale1);
    }
}
