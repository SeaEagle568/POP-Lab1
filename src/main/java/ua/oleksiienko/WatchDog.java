package ua.oleksiienko;

import java.util.List;

public class WatchDog implements Runnable {
    private final int sleepTime;
    private final List<WatchDogAwareThread> threads;
    public WatchDog(int sleepTime, List<WatchDogAwareThread> threads) {
        this.sleepTime = sleepTime;
        this.threads = threads;
    }
    @Override
    public void run() {
        startThreads();
        try {
            Thread.sleep(sleepTime);
            terminateThreads();
        } catch (InterruptedException e) {
            System.err.println("Watchdog: Application was interrupted! Stopping all threads...");
            terminateThreads();
            Thread.currentThread().interrupt();
        }
    }

    private void terminateThreads() {
        for (WatchDogAwareThread thread : threads) {
            thread.terminate();
        }
    }

    private void startThreads() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

}
