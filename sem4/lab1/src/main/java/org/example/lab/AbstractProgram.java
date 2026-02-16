package org.example.lab;

import java.util.Random;

public class AbstractProgram {

    public enum ProgramState {
        UNKNOWN,
        STOPPING,
        RUNNING,
        FATAL_ERROR
    }

    private ProgramState state = ProgramState.UNKNOWN;
    private boolean alive = false;
    private final Thread daemon;
    private static final Random random = new Random();
    private static final ProgramState[] STATES = {
            ProgramState.RUNNING,
            ProgramState.STOPPING,
            ProgramState.FATAL_ERROR
    };

    public AbstractProgram() {
        daemon = new Thread(() -> {
            synchronized (this) {
                try {
                    while (true) {
                        notifyAll();
                        wait();
                        Thread.sleep(1000);
                        state = STATES[random.nextInt(STATES.length)];
                        System.out.println("AbstractProgram state changed to " + state);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        daemon.setDaemon(true);
    }

    public synchronized void start() {
        if (!daemon.isAlive()) {
            daemon.start();
        }
        alive = true;
        state = ProgramState.RUNNING;
        System.out.println("AbstractProgram state changed to " + state);
        notifyAll();
    }

    public synchronized void shutdown() {
        alive = false;
        daemon.interrupt();
        notifyAll();
    }

    public synchronized boolean isAlive() {
        return alive;
    }

    public synchronized ProgramState getState() {
        return state;
    }
}
