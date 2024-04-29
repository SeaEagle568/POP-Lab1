package ua.oleksiienko;

public class CalculatorThread extends WatchDogAwareThread {
    private final int delta;
    public CalculatorThread(int delta, String name) {
        this.delta = delta;
        this.setName(name);
    }

    @Override
    public void run() {
        long sum = 0;
        while (isRunning()) {
            sum += delta;
        }
        System.out.printf("%s finished with result: %d\n",
                this.getName(), sum);
    }
}
