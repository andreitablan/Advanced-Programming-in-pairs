package serverClasses;

import java.io.IOException;

/**
 * This is the basic server class.
 */
public class Server {
    public static final int PORT = 8100;

    public Server() throws IOException {
        RunningServerSocket socket = new RunningServerSocket(PORT);
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }
}