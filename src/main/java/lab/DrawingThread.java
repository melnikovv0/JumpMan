package lab;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawingThread extends AnimationTimer {
	private final GraphicsContext gc;
	private final Game game;
	private long lastTime;

	public DrawingThread(Canvas canvas, Game game) {
		this.game = game; // Используем переданный объект Game
		this.gc = canvas.getGraphicsContext2D();
	}

	@Override
	public void handle(long now) {
		if (game.isGameOver()) { // Проверяем, завершена ли игра
			stop(); // Останавливаем таймер, если игра завершена
		} else {
			// Обычная логика рендеринга и обновления
			if (lastTime > 0) {
				double deltaT = (now - lastTime) / 1e9;
				game.simulate(deltaT);
			}
			game.draw(gc);
			lastTime = now;
		}
	}

}




