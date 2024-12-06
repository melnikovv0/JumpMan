package lab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.List;

class Ladder implements DrawableSimulable {
    private double x, y, width, height;
    private Image image;

    public Ladder(double x, double y, double width, double height, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = new Image(getClass().getResourceAsStream(imagePath));
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x, y, width, height);
    }

    @Override
    public void simulate(double deltaT, List<Platform> platforms, List<Ladder> ladders) {
        // Поскольку лестницы статичны, здесь ничего не делаем.
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