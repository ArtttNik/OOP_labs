package org.example.lab;

import java.time.Duration;
import java.util.Random;

public class AbstractProgram extends Thread {
    public enum ProgramState {
        UNKNOWN, STOPPING, RUNNING, FATAL_ERROR
    }

    private static final ProgramState[] RANDOM_STATES = {
            ProgramState.STOPPING,
            ProgramState.RUNNING,
            ProgramState.FATAL_ERROR
    };

    private ProgramState state = ProgramState.UNKNOWN;
    private static final Random random = new Random();
    private final Object monitor = new Object();
    private final Duration interval;

    public AbstractProgram(Duration interval) {
        this.interval = interval;
        setName("AbstractProgram");
    }

    @Override
    public void run() {
        Thread daemon = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(interval.toMillis());
                    synchronized (monitor) {
                        if (state == ProgramState.RUNNING) {
                            state = RANDOM_STATES[random.nextInt(RANDOM_STATES.length)];
                            System.out.println("AbstractProgram state changed to " + state);
                            monitor.notifyAll();
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        daemon.setName("AbstractProgram-daemon");
        daemon.setDaemon(true);
        daemon.start();

        synchronized (monitor) {
            state = ProgramState.RUNNING;
            System.out.println("AbstractProgram state changed to " + state);
            monitor.notifyAll();
        }

        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Я есть абстрактная программа, я работаю");
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        daemon.interrupt();
    }

    public void shutdown() {
        synchronized (monitor) {
            state = ProgramState.STOPPING;
            System.out.println("AbstractProgram state changed to " + state);
            monitor.notifyAll();
        }
        this.interrupt();
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