package serverClasses;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This is the class which is running the ServerSocket
 */
public class RunningServerSocket {
    public static int PORT = 0;
    public boolean running=true;
    public RunningServerSocket(int PORT) throws IOException {
        RunningServerSocket.PORT = PORT;

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);

            while (running) {
                System.out.println("Waiting for a client ...");

                Socket socket = serverSocket.accept();

                if(running)
                   new ClientThread(socket,this).start();
            }
            System.exit(0);
            
        } catch (IOException e) {
            System.err.println("Error at socket " + e);
        } finally {
            serverSocket.close();
        }
    }
}
