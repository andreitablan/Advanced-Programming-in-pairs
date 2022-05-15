package commands;

import serverClasses.RunningServerSocket;
import serverClasses.User;

public class CommandSend extends AbstractCommand {
    public CommandSend(RunningServerSocket runningServerSocket) {
        super(runningServerSocket);
    }

    public void sendMessage(String message, String name) {
        User user = new User();
        for (int index = 0; index < runningServerSocket.users.size(); index++) {
            if (runningServerSocket.users.get(index).name.equals(name)) {

                user = runningServerSocket.users.get(index);
            }
        }
        for(User user1:user.getFriends()){
            user1.addMessage(user,message);
        }
        
    }
}