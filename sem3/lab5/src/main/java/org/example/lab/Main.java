package org.example.lab;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // 1. Среднее значение
        System.out.println(StreamTasks.average(List.of(1, 2, 3, 4)));

        // 2. UpperCase + префикс
        System.out.println(
                StreamTasks.upperCaseWithPrefix(List.of("java", "stream"))
        );

        // 3. Квадраты уникальных элементов
        System.out.println(
                StreamTasks.uniqueSquares(List.of(1, 2, 2, 3, 4, 4, 5))
        );

        // 4. Фильтрация по букве + сортировка
        System.out.println(
                StreamTasks.filterAndSortByFirstLetter(
                        List.of("apple", "apricot", "banana"), 'a')
        );

        // 5. Последний элемент коллекции
        System.out.println(
                StreamTasks.getLastElement(List.of(10, 20, 30, 40))
        );

        // 6. Сумма чётных чисел массива
        System.out.println(
                StreamTasks.sumOfEvenNumbers(new int[]{1, 2, 3, 4, 6})
        );

        // 7. Преобразование списка строк в Map
        Map<Character, String> map =
                StreamTasks.listToMap(List.of("apple", "banana", "avocado"));

        System.out.println(map);
    }
}
