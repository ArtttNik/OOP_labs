package org.example;

import org.example.lab.AbstractProgram;
import org.example.lab.Supervisor;

public class Main {
    public static void main(String[] args) {
        AbstractProgram program = new AbstractProgram();
        Supervisor supervisor = new Supervisor(program);
        supervisor.start();
        program.start();
    }
}
