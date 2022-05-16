package commands;

import serverClasses.*;

/**
 * This method is responsible for executing the register command.
 */
public class CommandRegister extends AbstractCommand {

    public CommandRegister(RunningServerSocket runningServerSocket) {
        super(runningServerSocket);
    }

    /**
     * This method will add a new user.
     * @param name
     */
    public void addUser(String name) {
        User user = new User();
        user.setName(name);
        runningServerSocket.users.add(user);
    }

    /**
     * This method verifies if the given name already exists.
     * @param name
     * @return
     */
    public boolean verify(String name) {
        for (int index = 0; index < runningServerSocket.users.size(); index++)
            if (runningServerSocket.users.get(index).name.equals(name)) return true;
        return false;
    }
}
