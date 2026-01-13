package org.example.lab;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTasks {

    // 1. Среднее значение списка целых чисел
    public static double average(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    // 2. Приведение строк в верхний регистр + префикс "_new_"
    public static List<String> upperCaseWithPrefix(List<String> list) {
        return list.stream()
                .map(s -> "_new_" + s.toUpperCase())
                .collect(Collectors.toList());
    }

    // 3. Квадраты элементов, встречающихся ровно один раз
    public static List<Integer> uniqueSquares(List<Integer> list) {
        return list.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> e.getKey() * e.getKey())
                .collect(Collectors.toList());
    }

    // 4. Строки, начинающиеся с заданной буквы, отсортированные
    public static List<String> filterAndSortByFirstLetter(
            Collection<String> collection, char letter) {

        return collection.stream()
                .filter(s -> !s.isEmpty())
                .filter(s -> s.charAt(0) == letter)
                .sorted()
                .collect(Collectors.toList());
    }

    // 5. Последний элемент коллекции или исключение (без пользовательского исключения)
    public static <T> T getLastElement(Collection<T> collection) {
        return collection.stream()
                .reduce((first, second) -> second)
                .orElseThrow(NoSuchElementException::new);
    }

    // 6. Сумма чётных чисел массива
    public static int sumOfEvenNumbers(int[] array) {
        return IntStream.of(array)
                .filter(x -> x % 2 == 0)
                .sum();
    }

    // 7. Преобразование списка строк в Map:
    // первый символ — ключ, остальная часть — значение
    public static Map<Character, String> listToMap(List<String> list) {
        return list.stream()
                .filter(s -> s != null && s.length() > 0)
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1),
                        (existing, replacement) -> existing
                ));
    }
}