package org.example.lab;

public class Supervisor extends Thread {

    private final AbstractProgram program;

    public Supervisor(AbstractProgram program) {
        this.program = program;
    }

    @Override
    public void run() {
        synchronized (program) {
            try {
                while (program.isAlive()) {
                    program.wait();
                    AbstractProgram.ProgramState state = program.getState();
                    System.out.println("Supervisor detected state " + state);

                    switch (state) {
                        case STOPPING:
                            program.start();
                            System.out.println("Supervisor restarting program");
                            break;
                        case FATAL_ERROR:
                            program.shutdown();
                            System.out.println("Supervisor shutting down program");
                            return;
                        default:
                            break;
                    }
                    program.notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
