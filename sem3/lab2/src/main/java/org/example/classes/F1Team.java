package org.example.classes;

public class F1Team {
    private User driver;
    private String team;
    private int points;

    public F1Team(User driver, String team, int points) {
        this.driver = driver;
        this.team = team;
        this.points = points;
    }

    @Override
    public String toString() {
        return "Driver: " + driver + ", Team: " + team + ", Points: " + points;
    }
}