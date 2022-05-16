package commands;

import serverClasses.RunningServerSocket;
import serverClasses.User;

import java.util.HashMap;
import java.util.List;

/**
 * This class is responsible for executing the read command.
 */
public class CommandRead extends AbstractCommand {
    private User user;
    private List<User> friends;
    private HashMap<User, List<String>> messages;

    public CommandRead(RunningServerSocket runningServerSocket) {
        super(runningServerSocket);
    }

    /**
     * This method returns the received messages for a specific user.
     * @param name
     * @return
     */
    public String readMessages(String name) {

        String returnedMessages = "";

        for (User user1 : runningServerSocket.users) {
            if (user1.getName().equals(name)) {
                this.user = user1;
            }
        }

        friends = user.getFriends();
        messages = user.getMessages();

        for (User user2 : friends) {
            List<String> messagesFromOneFriend = messages.get(user2);
            returnedMessages = returnedMessages + " " + user2.getName() + ": ";
            for (int index = 0; index < messagesFromOneFriend.size(); index++) {
                returnedMessages = returnedMessages + messagesFromOneFriend.get(index) + " ";
            }
        }

        return returnedMessages;
    }
}
