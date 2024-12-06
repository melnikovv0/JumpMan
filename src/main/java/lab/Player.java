package lab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;


public class Player implements DrawableSimulable {
    private double x, y, width, height;
    private Image spriteRight, spriteLeft, currentSprite;
    private double velocityX, velocityY; // Добавляем вертикальную скорость
    private boolean facingRight;
    private static final double GRAVITY = 500; // Ускорение свободного падения
    private static final double JUMP_SPEED = -250; // Скорость прыжка
    private boolean isOnGround; // Флаг, указывающий, находится ли персонаж на земле
    private boolean isClimbing; // Флаг для обозначения, что игрок на лестнице

    private double climbSpeed = 20; // Скорость забирания по лестнице
    private int lives;  // Жизни игрока





    public Player(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.spriteRight = new Image(getClass().getResourceAsStream("/lab/pixel_man_right.png"));
        this.spriteLeft = new Image(getClass().getResourceAsStream("/lab/pixel_man_left.png"));
        this.currentSprite = spriteRight;
        this.velocityX = 0;
        this.velocityY = 0; // Начальная вертикальная скорость
        this.facingRight = true;
        this.lives = 3;

    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(currentSprite, x, y, width, height);
    }

    @Override
    public void simulate(double deltaT, List<Platform> platforms, List<Ladder> ladders) {
        checkForLadder(ladders);

        boolean onGround = false; // Локальная переменная для определения состояния на земле

        if (!isClimbing) {
            velocityY += GRAVITY * deltaT;
            for (Platform platform : platforms) {
                if (checkCollision(platform)) {
                    y = platform.getY() - height;
                    velocityY = 0;
                    onGround = true;
                    break; // Прерываем цикл, как только найдена платформа
                }
            }
        }

        isOnGround = onGround; // Обновляем флаг isOnGround после проверки всех платформ

        x += velocityX * deltaT;
        if (isClimbing) {
            velocityY = 0; // Обнуляем вертикальную скорость при забирании по лестнице
            y += velocityY * deltaT;
        } else {
            y += velocityY * deltaT;
        }


    }
    public void loseLife() {
        if (lives > 0) {
            lives--;
        }
    }

    public int getLives() {
        return lives;
    }

    private boolean checkCollision(Platform platform) {
        double playerBottomY = y + height;
        return playerBottomY > platform.getY() && playerBottomY < platform.getY() + platform.getHeight() &&
                x + width > platform.getX() && x < platform.getX() + platform.getWidth();
    }

    private void checkForLadder(List<Ladder> ladders) {
        isClimbing = false;
        for (Ladder ladder : ladders) {
            if (x + width > ladder.getX() && x < ladder.getX() + ladder.getWidth() &&
                    y + height > ladder.getY() && y < ladder.getY() + ladder.getHeight()) {
                isClimbing = true;

                break;
            }
        }
    }



    public void moveLeft() {
        facingRight = false;
        velocityX = -100;
        currentSprite = spriteLeft;
        System.out.println("Move Left");
    }

    public void moveRight() {
        facingRight = true;
        velocityX = 100;
        currentSprite = spriteRight;
        System.out.println("Move Right");
    }


    public void jump() {
        if (isOnGround && !isClimbing) {
            velocityY = JUMP_SPEED;
            System.out.println("Jump");
        } else {
            System.out.println("Jump Attempted, but Conditions Not Met");
        }
    }

    public void climbUp() {
        if (isClimbing) {
            y -= climbSpeed;
        }
    }

    public void climbDown() {
        if (isClimbing) {
            y += climbSpeed;
        }
    }




    public void stop() {
        velocityX = 0;
    }



    // Getters and setters
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