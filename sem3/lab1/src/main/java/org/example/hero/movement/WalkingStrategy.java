package org.example.hero.movement;

import java.awt.*;

public class WalkingStrategy  implements MovementStrategy {
    static final int SPEED = 5;

    @Override
    public void move(Point start, Point end) {
        double distance = start.distance(end);
        double time = distance/SPEED;

        System.out.printf("Walking distance is %.2f km\n", distance);
        System.out.printf("Walking time is %.2f hours\n", time);
    }
}
