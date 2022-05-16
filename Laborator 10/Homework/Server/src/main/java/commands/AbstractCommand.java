package commands;
import serverClasses.RunningServerSocket;

/**
 * This class is used for implementing the OOP modeling.
 */
public abstract class AbstractCommand {
    protected RunningServerSocket runningServerSocket;

    public AbstractCommand(RunningServerSocket runningServerSocket) {
        this.runningServerSocket = runningServerSocket;
    }
}
