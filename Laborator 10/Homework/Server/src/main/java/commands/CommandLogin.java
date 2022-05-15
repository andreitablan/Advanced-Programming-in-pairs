package commands;

import serverClasses.RunningServerSocket;

public class CommandLogin extends AbstractCommand{
    public CommandLogin(RunningServerSocket runningServerSocket) {
        super(runningServerSocket);
    }
    public boolean verify(String name)
    {
        for(int index=0; index<runningServerSocket.users.size();index++)
            if(runningServerSocket.users.get(index).name.equals(name)) {
                return true;}
        return false;
    }
}
