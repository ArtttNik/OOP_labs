package game;

public class TeleportStrategy implements MoveStrategy{
    @Override
    public double move(double distance) {
        double SPEED = 299_792_458.0;
        return distance / SPEED;
    }

    @Override
    public String toString() {
        return "Телепортироваться";
    }
}
