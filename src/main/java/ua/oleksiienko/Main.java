package ua.oleksiienko;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ERR_INPUT_MISMATCH = "Found invalid value in input - expected integer 0 < x <= 30.\nPlease try again:";

    public static void main(String[] args) {
        System.out.println("Lab 1, made by Oleksiienko Pavlo");
        System.out.println("Threads are calculating the arithmetical progression with a given delta.");
        final int threadsCount = extractFromSTDIN("number of threads");
        final int sleepSeconds = extractFromSTDIN("wait time in milliseconds");
        final int progressionDelta = extractFromSTDIN("integer progression delta");

        List<WatchDogAwareThread> threads = new LinkedList<>();
        for (int i = 1; i <= threadsCount; i++) {
            threads.add(new CalculatorThread(progressionDelta, "Calculator " + i));
        }
        WatchDog watchDog = new WatchDog(sleepSeconds, threads);
        System.out.println("Starting runners");
        new Thread(watchDog).start();
    }


    private static int extractFromSTDIN(String ask) {
        System.out.printf("Please enter %s:\n", ask);
        while (true) {
            try {
                int threadsCount = scanner.nextInt();
                if (threadsCount < 1 || threadsCount > 30) {
                    System.err.println(ERR_INPUT_MISMATCH);
                    continue;
                }
                return threadsCount;
            } catch (InputMismatchException e) {
                scanner.next();
                System.err.println(ERR_INPUT_MISMATCH);
            }
        }
    }
}