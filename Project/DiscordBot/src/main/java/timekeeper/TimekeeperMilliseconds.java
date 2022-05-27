package timekeeper;

import java.time.Duration;
import java.time.Instant;

public class TimekeeperMilliseconds implements Runnable,Questionable {
    private Instant start;
    private long timeElapsed;
    @Override
    public void run() {
        if (Thread.currentThread().isDaemon()) {
            start = Instant.now();
            timeElapsed = 0;
            while (true) {
                Instant finish = Instant.now();
                timeElapsed = Duration.between(start, finish).toMillis();
            }
        }
    }
    @Override
    public String getTimeElapsed(){
        return "I've been running for " + timeElapsed + " milliseconds";
    }
}
