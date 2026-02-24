package org.example.lab;

import java.time.Duration;

public class Supervisor extends Thread {
    private AbstractProgram program;
    private final Duration interval;

    public Supervisor(AbstractProgram program, Duration interval) {
        this.program = program;
        this.interval = interval;
        setName("Supervisor");
    }

    private void restartProgram() {
        program.shutdown();
        program = new AbstractProgram(interval);
        program.start();
    }

    @Override
    public void run() {
        try {
            AbstractProgram.ProgramState lastKnown = null;

            while (true) {
                AbstractProgram.ProgramState state = program.waitForStateChange(lastKnown);
                lastKnown = state;

                System.out.println("\u001B[7m¡" + "AbstractProgram state detected as " + state + "!\u001B[0m");

                switch (state) {
                    case UNKNOWN:
                        System.out.println("\u001B[7m¡" + "Supervisor starting program" + "!\u001B[0m");
                        program.start();
                        break;
                    case STOPPING:
                        System.out.println("\u001B[7m¡" + "Supervisor restarting program" + "!\u001B[0m");
                        restartProgram();
                        lastKnown = null;
                        break;
                    case FATAL_ERROR:
                        System.out.println("\u001B[7m¡" + "Supervisor shutting down program" + "!\u001B[0m");
                        program.shutdown();
                        return;
                    case RUNNING:
                        break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}