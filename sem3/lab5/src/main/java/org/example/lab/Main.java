package org.example.lab;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        System.out.println(Methods.average(List.of(1, 2, 3, 4)));
        System.out.println(Methods.average(List.of()));

        System.out.println(Methods.upperCaseWithPrefix(List.of("java", "stream")));

        System.out.println(Methods.uniqueSquares(List.of(1, 2, 3, 4, 2, 3, 5)));

        System.out.println(Methods.filterAndSortByFirstLetter(List.of("apple", "apricot", "banana"), 'a'));

        System.out.println(Methods.getLastElement(List.of(10, 20, 30, 40)));

        System.out.println(Methods.sumOfEvenNumbers(new int[]{1, 2, 3, 4, 6}));

        Map<Character, String> map = Methods.listToMap(List.of("apple", "banana", "avocado"));
        System.out.println(map);
    }
}
