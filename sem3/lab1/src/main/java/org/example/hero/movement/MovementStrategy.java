package org.example.hero.movement;
import org.example.point.Point;

public interface MovementStrategy {
    double move(Point start, Point end);
    String getName();
}
