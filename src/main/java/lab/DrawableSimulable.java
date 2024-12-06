package lab;

import javafx.scene.canvas.GraphicsContext;
import java.util.List;

public interface DrawableSimulable {
    void draw(GraphicsContext gc);
    void simulate(double deltaT, List<Platform> platforms, List<Ladder> ladders);
}
