package org.example.hero.movement;

import java.awt.*;

public class FlyingStrategy implements MovementStrategy {
    static final int SPEED = 800;

    @Override
    public void move(Point start, Point end) {
        double distance = start.distance(end);
        double time = distance/SPEED;

        System.out.printf("Flying distance is %.2f km\n", distance);
        System.out.printf("Flying time is %.2f hours\n", time);
    }
}
