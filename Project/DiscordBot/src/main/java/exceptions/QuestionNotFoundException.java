package exceptions;

import net.dv8tion.jda.api.entities.MessageChannel;

public class QuestionNotFoundException extends Exception {
    public QuestionNotFoundException(String msgString, MessageChannel channel) {
        channel.sendMessage("There is no definition for \"" + msgString + "\" in the database.").queue();
    }
}
