package lab;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
public class GameController {


    private Player player;
    private Scene scene;

    public GameController(Scene scene, Player player) {
        this.scene = scene;
        this.player = player;
        initControl();
    }

    private void initControl() {
        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnKeyReleased(this::handleKeyReleased);
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case A:
                player.moveLeft();
                break;
            case D:
                player.moveRight();
                break;
            case SPACE:
                player.jump();
                break;
            case W:
                player.climbUp();
                break;
            case S:
                player.climbDown();
                break;
        }
    }

    private void handleKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.D) {
            player.stop();
        }
    }
}