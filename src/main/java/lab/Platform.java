package lab;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public class Platform implements DrawableSimulable, Collisionable {
    private double x;
    private double y;
    private double width;
    private double height;

    public Platform(double x, double y, double width, double height, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        // Отрисовка платформы синим цветом
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, width, height);
    }

    @Override
    public void simulate(double deltaT, List<Platform> platforms, List<Ladder> ladders) {
        // Нет необходимости реализовывать этот метод, если платформы статичны
    }

    public boolean checkCollision(Player player) {
        // Проверяем, пересекается ли player с платформой  protíná
        double playerBottomY = player.getY() + player.getHeight();
        boolean isAbove = playerBottomY <= this.y;
        boolean isWithinXBounds = player.getX() + player.getWidth() > this.x && player.getX() < this.x + this.width;
        return isAbove && isWithinXBounds;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}