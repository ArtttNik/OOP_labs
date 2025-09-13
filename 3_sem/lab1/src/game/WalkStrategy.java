package game;

public class WalkStrategy implements MoveStrategy {
    @Override
    public double move(double distance) {
        double SPEED = 5.0;
        return distance / SPEED;
    }

    @Override
    public String toString() {
        return "Идти пешком";
    }
}