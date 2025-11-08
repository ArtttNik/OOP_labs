package org.example;

import org.example.hero.Hero;
import org.example.hero.movement.*;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

/*1.	В компьютерной игре герой (класс Hero) может перемещаться между двумя точками (метод move)
    различными способами: идти пешком, ехать на лошади, лететь и т. п. Реализовать классы,
    позволяющие выбирать и менять в ходе выполнения программы способ перемещения героя, используя
    паттерн “стратегия” (strategy). Продемонстрировать работу реализованных классов.
*/

public class Main {

    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printMenu();

        Hero hero = new Hero(new WalkingStrategy(), new Point(0, 0));

        while (true) {
            System.out.print("\nEnter your choice (0-4): ");

            int choice = readInt();
            if (choice == 0) {
                System.out.println("The end");
                break;
            }

            MovementStrategy strategy = chooseStrategy(choice);
            hero.setStrategy(strategy);

            Point destination = getRandomPoint();
            hero.move(destination);
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nChoose movement strategy:");
        System.out.println("1 - Walking");
        System.out.println("2 - Horse Riding");
        System.out.println("3 - Flying");
        System.out.println("4 - Teleporting");
        System.out.println("0 - Exit");
    }

    private static MovementStrategy chooseStrategy(int choice) {
        return switch (choice) {
            case 1 -> new WalkingStrategy();
            case 2 -> new HorseRidingStrategy();
            case 3 -> new FlyingStrategy();
            case 4 -> new TeleportStrategy();
            default -> null;
        };
    }

    private static Point getRandomPoint() {
        int x = random.nextInt(500);
        int y = random.nextInt(500);
        return new Point(x, y);
    }

    private static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value < 0 || value > 4) {
                    throw new IllegalArgumentException("Number must be between 0-4");
                }
                return value;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter an INTEGER between 0-4!");
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid choice! Please enter a number between 0-4!");
            }
        }
    }
}