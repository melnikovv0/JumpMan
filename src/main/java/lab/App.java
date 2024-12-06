package lab;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
public class App extends Application {

	private Canvas canvas;
	private AnimationTimer timer;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		canvas = new Canvas(650, 480);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);

		Game game = new Game(canvas.getWidth(), canvas.getHeight());
		GameController gameController = new GameController(scene, game.getPlayer());

		timer = new DrawingThread(canvas, game);
		timer.start();

		primaryStage.setScene(scene);
		primaryStage.setTitle("Java 1 - 1th laboratory");
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> System.exit(0));
	}

	@Override
	public void stop() throws Exception {
		timer.stop();
		super.stop();
	}
}
