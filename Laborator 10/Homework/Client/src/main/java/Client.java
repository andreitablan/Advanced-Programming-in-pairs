import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * This is the Client
 */
public class Client {
    public static void main(String[] args) throws IOException {

        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        boolean open = true;

        try (Socket socket = new Socket(serverAddress, PORT); PrintWriter output = new PrintWriter(socket.getOutputStream(), true); BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while (open) {

                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a request: ");
                String request = scanner.nextLine();
                output.println(request);
                String response = input.readLine();
                if (response.equals("You did exit from the serer!") || response.equals("Server stopped!")) {
                    open = false;
                    socket.close();
                    System.exit(0);
                }
                System.out.println(response);
            }

        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}