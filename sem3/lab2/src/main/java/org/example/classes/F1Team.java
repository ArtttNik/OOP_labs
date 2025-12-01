package org.example.classes;

public class F1Team {
    private User driver;
    private String teamName;
    private int points;

    public F1Team(User driver) {
        this.driver = driver;
        this.teamName = "McLaren";
        this.points = 0;
    }

    @Override
    public String toString() {
        return "Driver - " + driver + ", Points - " + points;
    }
}