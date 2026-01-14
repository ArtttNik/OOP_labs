package org.example.lab;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Methods {

    public static double average(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    public static List<String> upperCaseWithPrefix(List<String> list) {
        return list.stream()
                .map(s -> "_new_" + s.toUpperCase())
                .collect(Collectors.toList());
    }

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

    public static List<String> filterAndSortByFirstLetter(Collection<String> collection, char letter) {

        return collection.stream()
                .filter(s -> !s.isEmpty() && s.charAt(0) == letter)
                .sorted()
                .collect(Collectors.toList());
    }

    public static <T> T getLastElement(Collection<T> collection) {
        return collection.stream()
                .reduce((_, t) -> t)
                .orElseThrow();
    }

    public static int sumOfEvenNumbers(int[] array) {
        return IntStream.of(array)
                .filter(x -> x % 2 == 0)
                .sum();
    }

    public static Map<Character, String> listToMap(List<String> list) {
        return list.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1),
                        (_, s) -> s
                ));
    }
}