package lab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy {
    private double x, y;
    private double velocityX, velocityY;
    private final double maxSpeed;
    private final double screenWidth, screenHeight;

    public Enemy(double screenWidth, double screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.x = screenWidth / 2;
        this.y = screenHeight / 2;
        this.maxSpeed = 50; // Можно настроить скорость врага
    }

    public void simulate(double deltaT, Player player) {
        // Вычисляем разницу в позициях между врагом и игроком
        double deltaX = player.getX() - x;
        double deltaY = player.getY() - y;

        // Определяем, двигаться ли по горизонтали или вертикали
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            // Двигаться по горизонтали
            velocityX = maxSpeed * Math.signum(deltaX);
            velocityY = 0;
        } else {
            // Двигаться по вертикали
            velocityY = maxSpeed * Math.signum(deltaY);
            velocityX = 0;
        }

        // Проверка столкновения с игроком и сброс позиции в случае столкновения
        if (checkCollision(player)) {
            resetPosition();
        }

        // Обновляем позицию врага
        x += velocityX * deltaT;
        y += velocityY * deltaT;


    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillOval(x, y, 10, 10);
    }

    public boolean checkCollision(Player player) {
        // Предполагаем, что размер врага - 10x10
        return x < player.getX() + player.getWidth() &&
                x + 10 > player.getX() &&
                y < player.getY() + player.getHeight() &&
                y + 10 > player.getY();
    }



    public void resetPosition() {
        this.x = screenWidth / 2;
        this.y = screenHeight / 2;
    }
}