package org.example;

import org.example.lab.AbstractProgram;
import org.example.lab.Supervisor;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        AbstractProgram program = new AbstractProgram(Duration.ofSeconds(1));
        Supervisor supervisor = new Supervisor(program);
        supervisor.start();
    }
}