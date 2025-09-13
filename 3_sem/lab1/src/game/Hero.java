package game;

public class Hero {
    private double x, y;
    private MoveStrategy strategy;

    public Hero() {
        this.x = 0;
        this.y = 0;
    }

    public void setStrategy(MoveStrategy strategy) {
        this.strategy = strategy;
    }

    public void moveTo(double newX, double newY) {
        double distance = Math.sqrt(Math.pow(newX - x, 2) + Math.pow(newY - y, 2));

        double time = strategy.move(distance);

        System.out.println("Перемещение из (" + x + ", " + y + ") в (" + newX + ", " + newY + ").");
        System.out.println("Расстояние: " + distance + ", Время: " + time + " секунд. (" + strategy + ")");
        System.out.println();

        this.x = newX;
        this.y = newY;
    }
}
