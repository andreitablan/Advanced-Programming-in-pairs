import botConfiguration.DiscordBot;
import dataBase.AnswersRepository;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException {
        AnswersRepository answersRepository=new AnswersRepository();
        new DiscordBot().run();
       // System.out.println(answersRepository.findAll());
        //System.out.println(answersRepository.findById(1));
    }
}
