package serverClasses;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Timekeeper implements Runnable {

    float timeAccumulator;
    float frameTime;
    private Instant start;
    private int timeLimit = 1000000;
    private ClientThread clientThread;
    private int commandCounter=0;

    public Timekeeper(float frameTime, ClientThread clientThread) {
        this.clientThread = clientThread;
        timeAccumulator = 0;
        this.frameTime = frameTime;
    }

    public void run() {
        if (Thread.currentThread().isDaemon()) {
            start = Instant.now();
            System.out.println("The timer has started: 1000 seconds");
            long timeElapsed = 0;
            while (true) {
                if(commandCounter < clientThread.getCommandCounter()){
                    commandCounter+=1;
                    start=Instant.now();
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