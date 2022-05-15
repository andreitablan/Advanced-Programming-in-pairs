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
    public boolean verify(String name)
    {
        for(int index=0; index<runningServerSocket.users.size();index++)
            if(runningServerSocket.users.get(index).name.equals(name)) return true;
        return false;
    }
}
