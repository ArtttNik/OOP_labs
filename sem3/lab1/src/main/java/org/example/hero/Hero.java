package org.example.hero;

import org.example.hero.movement.MovementStrategy;
import java.awt.*;

public class Hero {
    public Point currentPoint;
    public MovementStrategy currentStrategy;

    public Hero(MovementStrategy movementStrategy, Point startPoint) {
        this.currentPoint = startPoint;
        this.currentStrategy = movementStrategy;
    }

    public void setStrategy(MovementStrategy newStrategy) {
        this.currentStrategy = newStrategy;
    }

    public void move(Point destination) {
        currentStrategy.move(currentPoint, destination);
        currentPoint = destination;
    }
}
