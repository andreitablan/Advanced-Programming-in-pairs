package botConfiguration;

import com.sun.syndication.io.FeedException;
import dataBase.*;
import dataBase.Manager;
import graphAlgorithms.DepthFirstSearch;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import rssReader.RssReader;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class DiscordBot extends ListenerAdapter{

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        if (msg.getContentRaw().equals("!ping")) {
            MessageChannel channel = event.getChannel();
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!") /* => RestAction<Message> */
                    .queue(response /* => Message */ -> {
                        response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                    });
        } else if (msg.getContentRaw().charAt(0) == '!') {
            String msgString = msg.getContentRaw().substring(1);
            MessageChannel channel = event.getChannel();
            AnswersRepository answersRepository = new AnswersRepository();
            channel.sendMessage(answersRepository.findByQuestion(msgString))
                    .queue(response -> response.editMessageFormat(answersRepository.findByQuestion(msgString)));
        } else if (msg.getContentRaw().charAt(0) == 'r' && msg.getContentRaw().charAt(1) == 's' && msg.getContentRaw().charAt(2) == 's' && msg.getContentRaw().charAt(3) == ':') {
            String link = msg.getContentRaw().substring(4);
            try {
                RssReader rssReader = new RssReader();
                String answer = rssReader.readRss(link);
                MessageChannel channel = event.getChannel();
                channel.sendMessage(answer).queue(response -> response.editMessageFormat(answer));
            } catch (FeedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (msg.getContentRaw().substring(0,4).equals("dfs:")) {
            String input = msg.getContentRaw().substring(5);//dfs: 4 2 0-1 0-2 1-2 2-0 2-3
            DepthFirstSearch dfs=new DepthFirstSearch(input);
            MessageChannel channel = event.getChannel();
            channel.sendMessage(dfs.getNodes()).queue(response -> response.editMessageFormat(dfs.getNodes()));
        }
    }


    public void run() throws LoginException, InterruptedException {

        JDA bot = JDABuilder.createLight("OTc5Mjg4NTI3MTI2NzkwMTU1.Guv5IA.Has0wrUnW60u0UrhiW1je4W9TbMZMZFGw59zBQ", GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new DiscordBot())
                .setActivity(Activity.playing("Scrie !ping"))
                .build();
        bot.awaitReady();
    }


}


