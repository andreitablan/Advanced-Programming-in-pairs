import botConfiguration.DiscordBot;
import com.sun.syndication.io.FeedException;
import dataBase.AnswersRepository;
import drawXML.NodesManager;
import graphAlgorithms.DepthFirstSearch;
import rssReader.*;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException, IOException, FeedException {
        AnswersRepository answersRepository = new AnswersRepository();
        //new DiscordBot().run();

        NodesManager nodesManager=new NodesManager();
        nodesManager.manageNodes("5 0-1 0-2 1-3 4-4");
    }
}
