package org.example.classes;

public class User {
    private String name;
    private int age;
    private F1Team team;

    public User() {
        this.name = "Fernando Alonso";
        this.age = 44;
    }

    public User(F1Team team) {
        this.name = "Lewis Hamilton";
        this.age = 25;
        this.team = team;
    }

    @Override
    public String toString() {
        return "name - " + name + ", age - " + age;
    }
}