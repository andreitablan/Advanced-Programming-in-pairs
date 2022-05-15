package serverClasses;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the class which is running the ServerSocket
 */
public class RunningServerSocket {
    public static int PORT = 0;
    public List<User> users = new ArrayList<User>();
    public boolean running=true;

    public RunningServerSocket(int PORT) throws IOException {
        RunningServerSocket.PORT = PORT;

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);

            while (running) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                ClientThread clientThread =new ClientThread(socket, this);
                clientThread.start();
                Timekeeper timer=new Timekeeper(0,clientThread);
                Thread threadTimer=new Thread(timer);
                threadTimer.setDaemon(true);
                threadTimer.start();
                new Timekeeper(0, clientThread);
            }
            
        } catch (IOException e) {
            System.err.println("Error at socket " + e);
        } finally {
            serverSocket.close();
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
