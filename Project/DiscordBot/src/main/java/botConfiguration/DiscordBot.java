package botConfiguration;

import dataBase.*;
import dataBase.Manager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class DiscordBot extends ListenerAdapter{

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        if (msg.getContentRaw().equals("!ping"))
        {
            MessageChannel channel = event.getChannel();
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!") /* => RestAction<Message> */
                    .queue(response /* => Message */ -> {
                        response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                    });
        }
        else if(msg.getContentRaw().charAt(0) == '!'){
            String msgString=msg.getContentRaw().substring(1);
            MessageChannel channel=event.getChannel();
            AnswersRepository answersRepository=new AnswersRepository();
                channel.sendMessage(answersRepository.findByQuestion(msgString))
                        .queue(response -> response.editMessageFormat(answersRepository.findByQuestion(msgString)));
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


