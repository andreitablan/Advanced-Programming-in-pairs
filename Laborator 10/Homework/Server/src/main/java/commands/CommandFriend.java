package commands;

import serverClasses.RunningServerSocket;
import serverClasses.User;

public class CommandFriend extends AbstractCommand{

    public CommandFriend(RunningServerSocket runningServerSocket) {
        super(runningServerSocket);
    }
    public void addFriend(String name, String friendName) {
        User user=new User();
        User userFriend=new User();

        for(int index=0; index<runningServerSocket.users.size();index++) {
            if (runningServerSocket.users.get(index).name.equals(name)) {

                user = runningServerSocket.users.get(index);
            }
            if(runningServerSocket.users.get(index).name.equals(friendName))
            {
                userFriend=runningServerSocket.users.get(index);
            }
        }
        user.addFriend(userFriend);
        userFriend.addFriend(user);
    }
}
