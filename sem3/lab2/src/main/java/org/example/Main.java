package org.example;

import org.example.invoker.Invoker;
import org.example.target.MyClass;

/*2.	Написать аннотацию с целочисленным параметром. Создать класс, содержащий публичные, защищенные
    и приватные методы (2-3 каждого вида) с параметрами, аннотировать любые из них. Вызвать из другого класса
    все аннотированные защищенные и приватные методы столько раз, сколько указано в параметре аннотации.
    Вызывающий методы код не должен зависеть от количества и типов параметров этих методов.*/

public class Main {
    public static void main(String[] args) {
        MyClass object = new MyClass();
        try {
            Invoker.invokeAnnotatedMethods(object);
        } catch (Exception e) {
            System.err.println("ERROR" + e.getMessage());
        }
    }
}