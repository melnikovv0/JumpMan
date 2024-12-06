package lab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Lives {
    private int count;
    private Image heartImage;
    private final int size = 30; // размер сердечка
    private final int startX = 5; // начальная X-координата
    private final int startY = 30; // Y-координата

    public Lives(int count) {
        this.count = count;
        this.heartImage = new Image(getClass().getResourceAsStream("/lab/heart.png"));
    }

    public void draw(GraphicsContext gc) {
        for (int i = 0; i < count; i++) {
            gc.drawImage(heartImage, startX + size * i, startY, size, size);
        }
    }



    public void setCount(int count) {
        this.count = count;
    }

    public void loseLife() {
        if (count > 0) {
            count--;
        }
    }

    public int getCount() {
        return count;
    }

}