package org.example.classes;

public class User {
    private String name;
    private int age;

    public User() {
        this.name = "Sebastian Vettel";
        this.age = 19;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name - " + name + ", age - " + age;
    }
}

