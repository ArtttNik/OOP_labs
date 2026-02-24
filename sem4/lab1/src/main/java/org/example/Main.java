package org.example;

import org.example.lab.AbstractProgram;
import org.example.lab.Supervisor;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        System.out.println("\u001B[7m" + "I am a supervisor =)" + "\u001B[0m");
        System.out.println("I am not =)\n");

        Duration interval = Duration.ofSeconds(1);
        AbstractProgram program = new AbstractProgram(interval);
        Supervisor supervisor = new Supervisor(program, interval);

        try {
            supervisor.start();
            supervisor.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nThe end!");
    }
}