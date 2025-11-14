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
        setStrategy(strategy);
    }

    public void setStrategy(MovementStrategy newStrategy) throws IllegalStateException {
        if (newStrategy == null)
            throw new IllegalStateException("Undefined behaviour because of bad strategy");

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
