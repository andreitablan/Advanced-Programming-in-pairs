import botConfiguration.DiscordBot;
import com.sun.syndication.io.FeedException;
import dataBase.AnswersRepository;
import drawXML.DrawGraph;
import drawXML.NodesManager;
import graphAlgorithms.DepthFirstSearch;
import rssReader.*;

import javax.security.auth.login.LoginException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException, IOException, FeedException, TransformerException {
        AnswersRepository answersRepository = new AnswersRepository();
        new DiscordBot().run();
    }
}
