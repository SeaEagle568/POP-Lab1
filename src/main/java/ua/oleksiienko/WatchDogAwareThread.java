package ua.oleksiienko;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class WatchDogAwareThread extends Thread {
    private final AtomicBoolean running = new AtomicBoolean(true);
    public void terminate() {
        running.set(false);
    }

    protected boolean isRunning() {
        return running.get();
    }
}
