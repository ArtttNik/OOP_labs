package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hero hero = new Hero();
        int choice = -1;

        System.out.println("Выберите стратегию перемещения:");
        System.out.println("1 - Пешком");
        System.out.println("2 - Лошадь");
        System.out.println("3 - Лететь");
        System.out.println("4 - Телепортация");
        System.out.println("0 - Выход");

        while (choice != 0) {
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: нужно ввести число!");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> hero.setStrategy(new WalkStrategy());
                case 2 -> hero.setStrategy(new HorseStrategy());
                case 3 -> hero.setStrategy(new FlyStrategy());
                case 4 -> hero.setStrategy(new TeleportStrategy());
                default -> {
                    System.out.println("Некорректный ввод!");
                    continue;
                }
            }

            System.out.print("Введите координаты X Y новой точки: ");

            if (!scanner.hasNextDouble()) {
                System.out.println("Ошибка: координата X должна быть числом!");
                scanner.next();
                continue;
            }
            double x = scanner.nextDouble();

            if (!scanner.hasNextDouble()) {
                System.out.println("Ошибка: координата Y должна быть числом!");
                scanner.next();
                continue;
            }
            double y = scanner.nextDouble();

            hero.moveTo(x, y);
        }
        System.out.println("Выход из программы...");

        scanner.close();
    }
}
