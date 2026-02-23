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
    private final Object monitor = new Object();
    private final Thread daemon;
    private static final Random random = new Random();
    private static final ProgramState[] STATES = {
            ProgramState.RUNNING,
            ProgramState.STOPPING,
            ProgramState.FATAL_ERROR
    };

    public AbstractProgram() {
        daemon = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(2000);
                    synchronized (monitor) {
                        if (!alive) break;
                        state = STATES[random.nextInt(STATES.length)];
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

    public boolean isAlive() {
        synchronized (monitor) {
            return alive;
        }
    }

    public ProgramState getState() {
        synchronized (monitor) {
            return state;
        }
    }

    public ProgramState waitForStateChange() throws InterruptedException {
        synchronized (monitor) {
            monitor.wait();
            return state;
        }
    }
}