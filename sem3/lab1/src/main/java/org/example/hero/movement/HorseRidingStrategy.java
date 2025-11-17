package org.example.hero.movement;
import org.example.point.Point;

public class HorseRidingStrategy implements MovementStrategy {
    static final int SPEED = 20;

    @Override
    public double move(Point start, Point end) {
        double distance = start.distance(end);
        double time = distance/SPEED;

        System.out.printf("Horse riding: distance %.2f km, time %.2f hours%n", distance, time);

        return distance;
    }

    @Override
    public String getName() {
        return "Horse Riding";
    }

    @Override
    public int getSpeed() {
        return SPEED;
    }
}
