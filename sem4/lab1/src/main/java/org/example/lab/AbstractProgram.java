package org.example.lab;

import java.time.Duration;
import java.util.Random;

public class AbstractProgram extends Thread {

    public enum ProgramState {
        UNKNOWN, STOPPING, RUNNING, FATAL_ERROR
    }

    private static final Random random = new Random();
    private static final ProgramState[] RANDOM_STATES = {
            ProgramState.STOPPING,
            ProgramState.RUNNING,
            ProgramState.FATAL_ERROR
    };

    private final Object monitor = new Object();
    private final Duration interval;

    private ProgramState state = ProgramState.UNKNOWN;

    public AbstractProgram(Duration interval) {
        this.interval = interval;
        setName("AbstractProgram");
    }

    @Override
    public void run() {
        Thread stateDaemon = new Thread(this::stateChanger);
        stateDaemon.setDaemon(true);
        stateDaemon.start();

        try {
            while (!isInterrupted()) {
                synchronized (monitor) {
                    while (state != ProgramState.RUNNING) {
                        monitor.wait();
                    }
                }

                System.out.println("Я есть абстрактная программа, я работаю");
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void stateChanger() {
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
    }

    public void startProgram() {
        synchronized (monitor) {
            if (state != ProgramState.RUNNING) {
                state = ProgramState.RUNNING;
                System.out.println("AbstractProgram state changed to RUNNING");
                monitor.notifyAll();
            }
        }
    }

    public void stopProgram() {
        synchronized (monitor) {
            state = ProgramState.STOPPING;
            System.out.println("AbstractProgram state changed to STOPPING");
            monitor.notifyAll();
        }
    }

    public void shutdown() {
        synchronized (monitor) {
            monitor.notifyAll();
        }
        interrupt();
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