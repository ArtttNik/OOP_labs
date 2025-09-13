package game;

public class HorseStrategy implements MoveStrategy {
    @Override
    public double move(double distance) {
        double SPEED = 15.0;
        return distance / SPEED;
    }

    @Override
    public String toString() {
        return "Ехать на лошади";
    }
}
