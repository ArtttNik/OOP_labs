package org.example;

import org.example.invoker.Invoker;
import org.example.annotation.ClassWithMethods;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nStart!");

        ClassWithMethods object = new ClassWithMethods();

        try {
            Invoker.invokeAnnotatedMethods(object);
        } catch (IllegalAccessException |  InstantiationException | NoSuchMethodException | IllegalStateException e) {
            System.err.println("ERROR " + e.getMessage());
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            System.err.println("ERROR: " + cause.getClass().getSimpleName());
        }

        System.out.println("\nExit!");
    }
}