package org.example.lab;

public class Supervisor extends Thread {

    private final AbstractProgram program;

    public Supervisor(AbstractProgram program) {
        this.program = program;
    }

    @Override
    public void run() {
        try {
            System.out.println("Supervisor detected state " + program.getState());

            program.start();

            while (program.isAlive()) {
                AbstractProgram.ProgramState state = program.waitForStateChange();
                System.out.println("Supervisor detected state " + state);

                switch (state) {
                    case STOPPING:
                        System.out.println("Supervisor restarting program");
                        program.start();
                        break;
                    case FATAL_ERROR:
                        System.out.println("Supervisor shutting down program");
                        program.shutdown();
                        return;
                    default:
                        break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}