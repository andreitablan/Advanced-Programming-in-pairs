package exceptions;

import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class QuestionNotFoundException extends Exception {
    public QuestionNotFoundException(String msgString, MessageChannel channel){
        channel.sendMessage("There is no definition for \"" + msgString + "\" in the database.").queue();
    }
}
