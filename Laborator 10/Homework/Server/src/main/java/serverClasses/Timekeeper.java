package serverClasses;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * This class measure the time for a specific user.
 */
public class Timekeeper implements Runnable {

    float frameTime;
    private Instant start;
    private int timeLimit = 100000;
    private ClientThread clientThread;
    private int commandCounter = 0;

    public Timekeeper(float frameTime, ClientThread clientThread) {
        this.clientThread = clientThread;
        this.frameTime = frameTime;
    }

    /**
     * This method counts the time.
     */
    public void run() {
        if (Thread.currentThread().isDaemon()) {
            start = Instant.now();
            System.out.println("The timer has started: 1000 seconds");
            long timeElapsed = 0;
            while (true) {
                if (commandCounter < clientThread.getCommandCounter()) {
                    commandCounter += 1;
                    start = Instant.now();
                    System.out.println("The timer was reset");
                }
                Instant finish = Instant.now();
                timeElapsed = Duration.between(start, finish).toMillis();
                if (timeElapsed > this.timeLimit) {
                    System.out.println("The time limit for the game has ended");
                    try {
                        clientThread.socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}