package org.example;

import org.example.hero.Hero;
import org.example.hero.movement.*;
import org.example.point.Point;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

/*1.	В компьютерной игре герой (класс Hero) может перемещаться между двумя точками (метод move)
    различными способами: идти пешком, ехать на лошади, лететь и т. п. Реализовать классы,
    позволяющие выбирать и менять в ходе выполнения программы способ перемещения героя, используя
    паттерн “стратегия” (strategy). Продемонстрировать работу реализованных классов.
*/

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Starting up.");
        printMenu();

        Hero hero = new Hero(new WalkingStrategy(), new Point(0, 0));

        int choice = readChoice();
        while (choice != 0) {

            try {
                hero.setStrategyByChoice(choice);
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
                scanner.close();
                System.out.println("Shutting down.");
                exit(1);
            }

            Point destination = randomPoint();
            hero.move(destination);

            choice = readChoice();
        }

        System.out.println("The end.");
        scanner.close();
        System.out.println("Shutting down.");
    }

    private static void printMenu() {
        System.out.println("\nChoose movement strategy:");
        System.out.println("1 - Walking");
        System.out.println("2 - Horse Riding");
        System.out.println("3 - Flying");
        System.out.println("4 - Teleporting");
        System.out.println("0 - Exit");
    }

    private static int readChoice() {
        while (true) {
            System.out.print("\nEnter choice (0–4): ");

            String input = scanner.nextLine();

            if (input.length() == 1) {
                char c = input.charAt(0);

                if (c >= '0' && c <= '4') {
                    return c - '0';
                }
            }

            System.err.println("Invalid input. Enter number 0–4.");

        }
    }

    private static Point randomPoint() {
        return new Point(random.nextInt(500), random.nextInt(500));
    }
}