package org.example;

import org.example.hero.Hero;
import org.example.hero.movement.*;
import org.example.point.Point;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Starting up.");

        Hero hero = new Hero(new WalkingStrategy(), new Point(0, 0));

        System.out.println("Current strategy: " + hero.getCurrentStrategyName());
        System.out.println("Starting point is " + hero.getCurrentPoint());

        printMenu();

        int choice = readChoice();
        while (choice != 0) {
            try {
                switch (choice) {
                    case 5:
                        String currentStrategy = hero.getCurrentStrategyName();

                        System.out.println("Current strategy: " + currentStrategy);
                        System.out.println("Speed: " + hero.getCurrentStrategySpeed() + " km");
                        break;

                    case 6:
                        Point destination = randomPoint();
                        hero.move(destination);
                        break;

                    default:
                        String previousStrategy = hero.getCurrentStrategyName();
                        hero.setStrategyByChoice(choice);
                        String newStrategy = hero.getCurrentStrategyName();

                        if (previousStrategy.equals(newStrategy)) {
                            System.out.println("Method not changed. Current method: " + newStrategy);
                        } else {
                            System.out.println("Selected strategy: " + newStrategy);
                            System.out.println("Speed: " + hero.getCurrentStrategySpeed() + " km");
                        }
                        System.out.println("Current movement method: " + newStrategy);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }

            choice = readChoice();
        }

        System.out.println("The end.");
        scanner.close();
        System.out.println("Shutting down.");
    }

    private static void printMenu() {
        System.out.println("\nChoose action:");
        System.out.println("1 - Set strategy as \"Walking\"");
        System.out.println("2 - Set strategy as \"Horse Riding\"");
        System.out.println("3 - Set strategy as \"Flying\"");
        System.out.println("4 - Set strategy as \"Teleporting\"");
        System.out.println("5 - Display current strategy");
        System.out.println("6 - Process moving");
        System.out.println("0 - Exit");
    }

    private static int readChoice() {
        while (true) {
            System.out.print("\nEnter choice (0-6): ");

            String input = scanner.nextLine();

            if (input.length() == 1) {
                char c = input.charAt(0);

                if (c >= '0' && c <= '6') {
                    return c - '0';
                }
            }

            System.err.println("Invalid input. Enter number 0-6.");
        }
    }

    private static Point randomPoint() {
        return new Point(random.nextInt(500), random.nextInt(500));
    }
}