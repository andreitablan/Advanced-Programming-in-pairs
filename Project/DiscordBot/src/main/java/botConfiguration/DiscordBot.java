package botConfiguration;

import com.sun.syndication.io.FeedException;
import dataBase.*;
import dataBase.Manager;
import drawXML.NodesManager;
import graphAlgorithms.BreadthFirstSearch;
import graphAlgorithms.ConnectedGraph;
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
        Message message = event.getMessage();
        if (message.getContentRaw().equals("!help")) {
            MessageChannel channel = event.getChannel();
            String output="You asked for help! \n" +
                    "You can use the following commands: \n" +
                    "1. rss:<url> \n"+
                    "2. !<question>\n" +
                    "3. dfs: <number of nodes> <starting node> <edges>\n" +
                    "4. bfs: <number of nodes> <starting node> <edges>\n" +
                    "5. connected: <number of nodes> <edges>";
            channel.sendMessage(output).queue();
        } else if (message.getContentRaw().charAt(0) == '!') {
            String msgString = message.getContentRaw().substring(1);
            MessageChannel channel = event.getChannel();
            AnswersRepository answersRepository = new AnswersRepository();
            channel.sendMessage(answersRepository.findByQuestion(msgString)).queue();
        } else if (message.getContentRaw().substring(0,4).equals("rss:")) {
            String link = message.getContentRaw().substring(4);
            try {
                RssReader rssReader = new RssReader();
                String answer = rssReader.readRss(link);
                MessageChannel channel = event.getChannel();
                channel.sendMessage(answer).queue();
            } catch (FeedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (message.getContentRaw().substring(0,4).equals("dfs:")) {
            String input = message.getContentRaw().substring(5);
            DepthFirstSearch depthFirstSearch=new DepthFirstSearch(input);
            MessageChannel channel = event.getChannel();
            channel.sendMessage(depthFirstSearch.getNodes()).queue();
        }
        else if (message.getContentRaw().substring(0,4).equals("bfs:")) {
            String input = message.getContentRaw().substring(5);
            BreadthFirstSearch breadthFirstSearch=new BreadthFirstSearch(input);
            MessageChannel channel = event.getChannel();
            channel.sendMessage(breadthFirstSearch.getNodes()).queue();
        }
        else if (message.getContentRaw().substring(0,10).equals("connected:")) {
            String input = message.getContentRaw().substring(11);
            ConnectedGraph connectedGraph=new ConnectedGraph();
            MessageChannel channel = event.getChannel();
            channel.sendMessage(connectedGraph.checkConnected(input)).queue();
        }
        else if (message.getContentRaw().substring(0,5).equals("draw:")) {
            String input = message.getContentRaw().substring(6);
            NodesManager nodesManager=new NodesManager();
            nodesManager.manageNodes(input);
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Am primit comanda de afisare").queue();
        }

    }


    public void run() throws LoginException, InterruptedException {

        JDA bot = JDABuilder.createLight("OTc5Mjg4NTI3MTI2NzkwMTU1.Guv5IA.Has0wrUnW60u0UrhiW1je4W9TbMZMZFGw59zBQ",
                        GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new DiscordBot())
                .setActivity(Activity.playing("Scrie !ping"))
                .build();
        bot.awaitReady();
    }


}


