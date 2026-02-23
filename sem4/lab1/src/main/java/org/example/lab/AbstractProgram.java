package org.example.lab;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;

public class AbstractProgram {

    public enum ProgramState {
        UNKNOWN,
        STOPPING,
        RUNNING,
        FATAL_ERROR
    }

    private static final ProgramState[] RANDOM_STATES = Arrays.stream(ProgramState.values())
            .filter(s -> s != ProgramState.UNKNOWN)
            .toArray(ProgramState[]::new);

    private ProgramState state = ProgramState.UNKNOWN;
    private boolean alive = false;
    private final Object monitor = new Object();
    private final Thread daemon;
    private static final Random random = new Random();

    public AbstractProgram(Duration interval) {
        daemon = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(interval.toMillis());
                    synchronized (monitor) {
                        if (!alive) break;
                        state = RANDOM_STATES[random.nextInt(RANDOM_STATES.length)];
                        System.out.println("AbstractProgram state changed to " + state);
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        daemon.setDaemon(true);
    }

    public void start() {
        synchronized (monitor) {
            if (!daemon.isAlive()) {
                daemon.start();
            }
            alive = true;
            state = ProgramState.RUNNING;
            System.out.println("AbstractProgram state changed to " + state);
            monitor.notifyAll();
        }
    }

    public void shutdown() {
        synchronized (monitor) {
            alive = false;
            daemon.interrupt();
            monitor.notifyAll();
        }
    }

    public ProgramState waitForStateChange(ProgramState lastKnown) throws InterruptedException {
        synchronized (monitor) {
            while (state == lastKnown) {
                monitor.wait();
            }
            return state;
        }
    }
}