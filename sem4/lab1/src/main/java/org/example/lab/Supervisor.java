package org.example.lab;

public class Supervisor extends Thread {

    private final AbstractProgram program;

    public Supervisor(AbstractProgram program) {
        this.program = program;
    }

    @Override
    public void run() {
        try {
            AbstractProgram.ProgramState lastKnown = null;

            while (true) {
                AbstractProgram.ProgramState state = program.waitForStateChange(lastKnown);
                lastKnown = state;
                System.out.println("Supervisor detected state " + state);

                switch (state) {
                    case UNKNOWN:
                        program.start();
                        break;
                    case STOPPING:
                        System.out.println("Supervisor restarting program");
                        program.start();
                        break;
                    case FATAL_ERROR:
                        System.out.println("Supervisor shutting down program");
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