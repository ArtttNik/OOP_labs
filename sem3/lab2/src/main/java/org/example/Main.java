package org.example;

import org.example.invoker.Invoker;
import org.example.annotation.ClassWithMethods;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nStart!");

        ClassWithMethods object = new ClassWithMethods();

        try {
            Invoker.invokeAnnotatedMethods(object);
        } catch (Exception e) {
            System.err.println("ERROR" + e.getMessage());
        }

        System.out.println("\nExit!");
    }
}