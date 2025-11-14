package org.example.hero.movement;
import org.example.point.Point;

public class TeleportStrategy implements MovementStrategy {
    static final int SPEED = 1_079_252_848;

    @Override
    public double move(Point start, Point end) {
        double distance = start.distance(end);
        double time = distance/SPEED;

        System.out.printf("Teleporting: distance %.2f km, time %.2f hours%n", distance, time);

        return distance;
    }

    @Override
    public String getName() {
        return "Teleporting";
    }
}
