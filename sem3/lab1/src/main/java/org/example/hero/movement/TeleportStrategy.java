package org.example.hero.movement;

import java.awt.*;

public class TeleportStrategy implements MovementStrategy {
    static final int SPEED = 1_079_252_848; //это км/час

    @Override
    public void move(Point start, Point end) {
        double distance = start.distance(end);
        double time = distance/SPEED;

        System.out.printf("Teleporting distance is %.2f km\n", distance);
        System.out.printf("Teleporting time is %.2f hours\n", time);
    }
}
