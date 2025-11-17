package org.example.hero;

import org.example.hero.movement.*;
import org.example.point.Point;

public class Hero {
    private Point currentPoint;
    private MovementStrategy currentStrategy;

    private double totalDistance = 0;
    private int strategyChanges = 0;

    public Hero(MovementStrategy strategy, Point startPoint) {
        this.currentPoint = startPoint;
        this.currentStrategy = strategy;
    }

    public String getCurrentStrategyName() {
        if (currentStrategy == null) {
            return null;
        }
        return currentStrategy.getName();
    }

    public double getCurrentStrategySpeed() {
        if (currentStrategy == null) {
            return 0.0;
        }
        return currentStrategy.getSpeed();
    }

    public void setStrategy(MovementStrategy newStrategy) throws IllegalStateException {
        if (newStrategy == null) {
            throw new IllegalStateException("Cannot set null strategy");
        }

        if (this.currentStrategy != null && !this.currentStrategy.getName().equals(newStrategy.getName())) {
            strategyChanges++;
        }

        this.currentStrategy = newStrategy;
    }

    public void setStrategyByChoice(int choice) throws IllegalArgumentException {
        MovementStrategy newStrategy = createStrategy(choice);
        setStrategy(newStrategy);
    }

    private MovementStrategy createStrategy(int choice) throws IllegalArgumentException {
        return switch (choice) {
            case 1 -> new WalkingStrategy();
            case 2 -> new HorseRidingStrategy();
            case 3 -> new FlyingStrategy();
            case 4 -> new TeleportStrategy();
            default -> throw new IllegalArgumentException("Invalid strategy choice: " + choice);
        };
    }

    public void move(Point destination) {
        if (currentStrategy == null) {
            throw new IllegalStateException("Cannot move without strategy");
        }

        double distance = currentStrategy.move(currentPoint, destination);
        totalDistance += distance;
        currentPoint = destination;

        printStats();
    }

    private void printStats() {
        System.out.printf("\nStrategy: %s, \nTotal distance: %.2f, Strategy changes: %d%n",
                currentStrategy.getName(), totalDistance, strategyChanges);
    }
}
