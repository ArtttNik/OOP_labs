package org.example.classes;

public class F1Team {
    private User driver;
    private String team;
    private int points;

    public F1Team() {
        this.team = "DEFAULT F1Team";
        this.points = 100;
        this.driver = new User();
    }

    public F1Team(String name) {
        this.team = name;
        this.points = 100;
    }

    @Override
    public String toString() {
        return "Driver - " + driver + ", Team - " + team + ", Points - " + points;
    }
}