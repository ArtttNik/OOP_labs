package org.example.hero;

import org.example.hero.movement.MovementStrategy;
import org.example.point.Point;


public class Hero {
    private Point currentPoint;
    private MovementStrategy currentStrategy;

    private double totalDistance = 0;
    private int strategyChanges = 0;

    public Hero(MovementStrategy strategy, Point startPoint) {
        this.currentPoint = startPoint;
        setStrategy(strategy);
    }

    public void setStrategy(MovementStrategy newStrategy) {
        if (newStrategy == null)
            return;

        if (this.currentStrategy != null && !this.currentStrategy.getName().equals(newStrategy.getName())) {
            strategyChanges++;
        }

        this.currentStrategy = newStrategy;
    }

    public void move(Point destination) {
        double distance = currentStrategy.move(currentPoint, destination);
        totalDistance += distance;
        currentPoint = destination;

        printStats();
    }

    private void printStats() {
        System.out.printf("Strategy: %s, Distance total: %.2f, Changes: %d%n",
                currentStrategy.getName(), totalDistance, strategyChanges);
    }
}
