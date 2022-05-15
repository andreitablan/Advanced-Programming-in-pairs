package commands;

import serverClasses.RunningServerSocket;

import java.io.Serializable;

public abstract class AbstractCommand {
    protected RunningServerSocket runningServerSocket;

    public AbstractCommand(RunningServerSocket runningServerSocket) {
        this.runningServerSocket = runningServerSocket;
    }
}
