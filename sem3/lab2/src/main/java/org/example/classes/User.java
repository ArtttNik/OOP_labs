package org.example.classes;

public class User {
    private String name;
    private int age;
    private F1Team team;

    public User() {
        this.name = "Fernando Alonso";
        this.age = 44;
        this.team = new F1Team("Lotus");
    }

    @Override
    public String toString() {
        return "name - " + name + ", age - " + age;
    }
}