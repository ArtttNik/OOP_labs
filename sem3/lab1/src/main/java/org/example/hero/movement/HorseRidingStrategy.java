package org.example.hero.movement;

import java.awt.*;

public class HorseRidingStrategy implements MovementStrategy {
    static final int SPEED = 20;

    @Override
    public void move(Point start, Point end) {
        double distance = start.distance(end);
        double time = distance/SPEED;

        System.out.printf("Riding a horse distance is %.2f km\n", distance);
        System.out.printf("Riding a horse time is %.2f hours\n", time);
    }
}
