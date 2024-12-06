package lab;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Coin {
    private double x, y;
    private double width, height;
    private Image image;
    private boolean isVisible; // Флаг для отслеживания видимости монетки

    public Coin(double x, double y, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = 20; // Указать размеры монетки
        this.height = 20;
        this.image = new Image(getClass().getResourceAsStream(imagePath));
        this.isVisible = true; // Монетка изначально видима
    }

    public void draw(GraphicsContext gc) {
        if (isVisible) {
            gc.drawImage(image, x, y, width, height);
        }
    }

    // Геттеры для координат и размеров
    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }

    // Сеттер для видимости
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    public boolean isVisible() {
        return isVisible;
    }
}
