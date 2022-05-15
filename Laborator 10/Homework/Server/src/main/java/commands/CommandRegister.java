package commands;
import serverClasses.*;
public class CommandRegister extends AbstractCommand {

    public CommandRegister(RunningServerSocket runningServerSocket) {
        super(runningServerSocket);
    }
    public void addUser(String name)
    {
        User user=new User();
        user.setName(name);
        runningServerSocket.users.add(user);
    }
}
