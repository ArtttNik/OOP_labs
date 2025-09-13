package game;

public class FlyStrategy implements MoveStrategy {
    @Override
    public double move(double distance) {
        double SPEED = 50.0;
        return distance / SPEED;
    }

    @Override
    public String toString() {
        return "Лететь";
    }
}