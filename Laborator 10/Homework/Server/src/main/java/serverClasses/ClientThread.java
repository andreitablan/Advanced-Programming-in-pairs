package serverClasses;

import commands.*;
import utils.SVGGenerator;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This is the class responsible with a client Thread
 */
class ClientThread extends Thread {
    public Socket socket = null;
    public String loggedUserName = null;
    public RunningServerSocket runningServerSocket = null;
    private int commandCounter = 0;

    public ClientThread(Socket socket, RunningServerSocket runningServerSocket) {
        this.socket = socket;
        this.runningServerSocket = runningServerSocket;
    }

    /**
     * This is the method for running the client thread.
     */
    public void run() {
        try {
            while (true) {

                String exit = "exit";
                String stop = "stop";
                String register = "register";
                String login = "login";
                String friend = "friend";
                String send = "send";
                String read = "read";
                String svg = "svg";

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = input.readLine();
                String[] requestArguments = request.split(" ");

                PrintWriter output = new PrintWriter(socket.getOutputStream());

                this.commandCounter += 1;

                if (requestArguments[0].equals(read) && this.loggedUserName != null) {
                    CommandRead commandRead = new CommandRead(runningServerSocket);
                    String answer = commandRead.readMessages(this.loggedUserName);
                    output.println(answer);
                    output.flush();

                } else if (requestArguments[0].equals(send) && this.loggedUserName != null) {
                    String message = "";
                    for (int index = 1; index < requestArguments.length; index++)
                        message = message + " " + requestArguments[index];
                    CommandSend commandSend = new CommandSend(runningServerSocket);
                    commandSend.sendMessage(message, this.loggedUserName);
                    String answer = "Server received the request " + request + ".";
                    output.println(answer);
                    output.flush();

                } else if (requestArguments[0].equals(friend) && this.loggedUserName != null) {
                    CommandFriend commandFriend = new CommandFriend(runningServerSocket);
                    for (int index = 1; index < requestArguments.length; index++)
                        commandFriend.addFriend(this.loggedUserName, requestArguments[index]);
                    String answer = "Server received the request " + request + ".";
                    output.println(answer);
                    output.flush();

                } else if (requestArguments[0].equals(login)) {
                    CommandLogin commandLogin = new CommandLogin(runningServerSocket);
                    if (commandLogin.verify(requestArguments[1])) {
                        this.loggedUserName = requestArguments[1];
                        String answer = "You did login as " + requestArguments[1] + ".";
                        output.println(answer);
                    } else {
                        String answer = "The user " + requestArguments[1] + " does not exist!";
                        output.println(answer);
                    }
                    output.flush();

                } else if (requestArguments[0].equals(register)) {
                    CommandRegister commandRegister = new CommandRegister(runningServerSocket);
                    if (!commandRegister.verify(requestArguments[1])) {
                        commandRegister.addUser(requestArguments[1]);
                        String answer = "You did registered as " + requestArguments[1] + ".";
                        output.println(answer);
                        this.loggedUserName = requestArguments[1];
                    } else {
                        String answer = "The user " + requestArguments[1] + " already exists!";
                        output.println(answer);
                    }
                    output.flush();

                } else if (requestArguments[0].equals(exit)) {
                    String answer = "You did exit from the serer!";
                    output.println(answer);
                    output.flush();
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    break;

                } else if (requestArguments[0].equals(stop)) {
                    String answer = "Server will stop after exiting running clients!";
                    output.println(answer);
                    output.flush();
                    runningServerSocket.setRunning(false);

                } else if (requestArguments[0].equals(svg)) {
                    SVGGenerator svgGenerator = new SVGGenerator(runningServerSocket.users);
                    String answer = "Exported the SVG.";
                    output.println(answer);
                    output.flush();

                } else {
                    String answer = "Wrong command! Please send one of the following: register, login, friend, send, read, exit, stop, svg.";
                    output.println(answer);
                    output.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error" + e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCommandCounter() {
        return commandCounter;
    }
}