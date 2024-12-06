
package lab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Platform> platforms; // Список для хранения всех платформ
    private List<Ladder> ladders;
    private List<Coin> coins;
    private Player player;

    private Enemy enemy;
    private Lives lives;

    private final double playerWidth = 30; // Ширина персонажа
    private final double playerHeight = 30; // Высота персонажа
    private int score;
    private boolean gameOver = false;

    private final double screenWidth;
    private final double screenHeight;


    public Game(double width, double height) {

        this.screenWidth = width;
        this.screenHeight = height;

        platforms = new ArrayList<>();
        ladders = new ArrayList<>();
        coins = new ArrayList<>();

        enemy = new Enemy(width, height);

        this.score = 0; // Начальное количество очков


        // Толщина платформы
        final double platformHeight = 10;

        // Платформа на самом низу
        platforms.add(new Platform(0, height - platformHeight, width, platformHeight, null));


        // Платформа на самом низу
        platforms.add(new Platform(0, height - platformHeight, width, platformHeight, null));

        // Второй уровень платформ, изгибающихся к краям
        platforms.add(new Platform(0, height * 0.75, width * 0.30, platformHeight, null));
        platforms.add(new Platform(width - width * 0.30, height * 0.75, width * 0.30, platformHeight, null));

        platforms.add(new Platform(width * 0.45, height * 0.75, width * 0.10, platformHeight, null));


        // Третий уровень платформ, четыре маленькие по центру
        double centerPlatformWidth = width * 0.15;
        double centerPlatformX = (width - centerPlatformWidth * 4) / 2;
        for (int i = 0; i < 4; i++) {
            platforms.add(new Platform(centerPlatformX + centerPlatformWidth * i, height * 0.55, centerPlatformWidth, platformHeight, null));
        }
        platforms.add(new Platform(width * 0.45, height * 0.35, width * 0.10, platformHeight, null));


        // Четвертый уровень, две платформы по краям
        platforms.add(new Platform(width * 0.05, height * 0.35, width * 0.25, platformHeight, null));
        platforms.add(new Platform(width - width * 0.30, height * 0.35, width * 0.25, platformHeight, null));

        // Верхний уровень, большая центральная платформа
        platforms.add(new Platform(width * 0.25, height * 0.15, width * 0.50, platformHeight, null));

// Добавляем лестницы между вторым и третьим уровнями
        ladders.add(new Ladder(width * 0.25 - 10, height * 0.75, 20, height * 0.25, "/lab/ladder.png"));

        ladders.add(new Ladder(width * 0.75 - 10, height * 0.75, 20, height * 0.25, "/lab/ladder.png"));

        ladders.add(new Ladder(width * 0.5 - 10, height * 0.55, 20, height * 0.20, "/lab/ladder.png"));


        // Лестницы между третьим и четвертым уровнями
        ladders.add(new Ladder(width * 0.25 - 10, height * 0.35, 20, height * 0.2, "/lab/ladder.png"));

        ladders.add(new Ladder(width * 0.75 - 10, height * 0.35, 20, height * 0.2, "/lab/ladder.png"));

        // Лестница между четвертым и пятым уровнями
        ladders.add(new Ladder(width * 0.5 - 10, height * 0.15, 20, height * 0.20, "/lab/ladder.png"));

        double playerStartX = (width - playerWidth) / 2; // Центр платформы по X минус половина ширины персонажа
        double playerStartY = height - platformHeight - playerHeight; // Верх платформы минус высота персонажа

        // Создаем персонажа с этими координатами
        player = new Player(playerStartX, playerStartY, playerWidth, playerHeight);

        for (Platform platform : platforms) {
            double coinX = platform.getX() + platform.getWidth() / 2 - 10; // Центр платформы минус половина ширины монетки
            double coinY = platform.getY() - 20; // Чуть выше платформы
            coins.add(new Coin(coinX, coinY, "/lab/coin.png"));
        }

        score = 0;

        lives = new Lives(3); // Например, начальное количество жизней равно 3
    }
    //  Kontroluje kolizi hráče s mincemi a sbírá je.
    private boolean checkPlayerCoinCollision(Coin coin) {
        return player.getX() < coin.getX() + coin.getWidth() &&
                player.getX() + player.getWidth() > coin.getX() &&
                player.getY() < coin.getY() + coin.getHeight() &&
                player.getY() + player.getHeight() > coin.getY();
    }

    public void simulate(double deltaT) {
        if (!gameOver) {
            player.simulate(deltaT, platforms, ladders);
            enemy.simulate(deltaT, player); // Добавляем player как второй параметр

            // Проверяем сбор монеток
            for (Coin coin : coins) {
                if (coin.isVisible() && checkPlayerCoinCollision(coin)) {
                    coin.setVisible(false); // Скрываем монетку
                    score += 10; // Увеличиваем счет
                }
            }


            if (enemy.checkCollision(player)) {
                player.loseLife();  // Изменение состояния жизней игрока
                lives.setCount(player.getLives());  // Обновление состояния объекта Lives
                enemy.resetPosition();
                if (lives.getCount() <= 0) {
                    gameOver = true; // Установите флаг gameOver в true
                }
            }

        }
    }




    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        gc.setFill(Color.BLUE); // Используем синий цвет для платформ
        for (Platform platform : platforms) {
            platform.draw(gc);
        }

        for (Ladder ladder : ladders) {
            ladder.draw(gc);
        }

        for (Coin coin : coins) {
            coin.draw(gc);
        }


        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + score, 10, 20); // Отображение очков


        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Arial", 40)); // Установите желаемый шрифт и размер
            gc.fillText("Game Over", screenWidth / 2 - 100, screenHeight / 2); // Позиция текста по центру
        }



        enemy.draw(gc);

        player.draw(gc);

        lives.draw(gc);

    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean isGameOver() {
        return gameOver;
    }



}