package serverClasses;

import commands.*;
import utils.TestSVGGen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * This is the class responsible with a client Thread
 */
class ClientThread extends Thread {
    public Socket socket = null;
    private int commandCounter = 0;
    public String loggedUserName = null;
    public RunningServerSocket runningServerSocket = null;

    public ClientThread(Socket socket, RunningServerSocket runningServerSocket) {
        this.socket = socket;
        this.runningServerSocket = runningServerSocket;
    }

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
                    CommandRead commandRead= new CommandRead(runningServerSocket);
                    String raspuns = commandRead.readMessages(this.loggedUserName);
                    output.println(raspuns);
                    output.flush();

                } else if (requestArguments[0].equals(send) && this.loggedUserName != null) {
                    String message = "";
                    for (int index = 1; index < requestArguments.length; index++)
                        message = message + " " + requestArguments[index];
                    CommandSend commandSend = new CommandSend(runningServerSocket);
                    commandSend.sendMessage(message, this.loggedUserName);
                    String raspuns = "serverClasses.Server received the request " + request + ".";
                    output.println(raspuns);
                    output.flush();

                } else if (requestArguments[0].equals(friend) && this.loggedUserName != null) {
                    CommandFriend commandFriend = new CommandFriend(runningServerSocket);
                    for (int index = 1; index < requestArguments.length; index++)
                        commandFriend.addFriend(this.loggedUserName, requestArguments[index]);
                    String raspuns = "Server received the request " + request + ".";
                    output.println(raspuns);
                    output.flush();

                } else if (requestArguments[0].equals(login)) {
                    CommandLogin commandLogin = new CommandLogin(runningServerSocket);
                    if (commandLogin.verify(requestArguments[1])) {
                        this.loggedUserName = requestArguments[1];
                        String raspuns = "You did login as " + requestArguments[1] + ".";
                        output.println(raspuns);
                    } else {
                        String raspuns = "The user " + requestArguments[1] + " does not exist!";
                        output.println(raspuns);
                    }
                    output.flush();

                } else if (requestArguments[0].equals(register)) {
                    CommandRegister commandRegister = new CommandRegister(runningServerSocket);
                    if (!commandRegister.verify(requestArguments[1])) {
                        commandRegister.addUser(requestArguments[1]);
                        String raspuns = "You did registered as " + requestArguments[1] + ".";
                        output.println(raspuns);
                        this.loggedUserName = requestArguments[1];
                    } else {
                        String raspuns = "The user " + requestArguments[1] + " already exists!";
                        output.println(raspuns);
                    }
                    output.flush();

                } else if (requestArguments[0].equals(exit)) {
                    String raspuns = "You did exit from the serer!";
                    output.println(raspuns);
                    output.flush();
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    break;

                } else if (requestArguments[0].equals(stop)) {
                    String raspuns = "Server will stop after exiting running clients!";
                    output.println(raspuns);
                    output.flush();
                    runningServerSocket.setRunning(false);
                }else if(requestArguments[0].equals(svg)) {
                    TestSVGGen testSVGGen=new TestSVGGen(runningServerSocket.users);
                    String raspuns = "Exported the SVG.";
                    output.println(raspuns);
                    output.flush();
                }
                else {
                    String raspuns = "Wrong command! Please send one of the following: register, login, friend, send, read, exit, stop.";
                    output.println(raspuns);
                    output.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error" + e);
        }
    }

    public int getCommandCounter() {
        return commandCounter;
    }
}