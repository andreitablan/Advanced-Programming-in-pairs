package commands;

import serverClasses.RunningServerSocket;

/**
 * This class is responsible for executing the login command.
 */
public class CommandLogin extends AbstractCommand {
    public CommandLogin(RunningServerSocket runningServerSocket) {
        super(runningServerSocket);
    }

    /**
     * This method verifies if the name of an user exists in the user list.
     * @param name
     * @return
     */
    public boolean verify(String name) {
        for (int index = 0; index < runningServerSocket.users.size(); index++)
            if (runningServerSocket.users.get(index).name.equals(name)) {
                return true;
            }
        return false;
    }
}
