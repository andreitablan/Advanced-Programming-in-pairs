import botConfiguration.DiscordBot;
import com.sun.syndication.io.FeedException;
import dataBase.AnswersRepository;
import rssReader.*;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException, IOException, FeedException {
        AnswersRepository answersRepository = new AnswersRepository();
        new DiscordBot().run();
    }
}
