import javax.swing.*;
import java.awt.*;

public class Game {
    private final double TARGET_FPS = 60;
    private final double TARGET_FRAME_TIME = 1000000000 / TARGET_FPS;

    // precisamos de uma thread para fazer o loop esperar
    // o período
    private Thread thread;
    private boolean isRunning = false;

    public Scene getCurrentScene() {
        return currentScene;
    }

    private Scene currentScene;

    public Game() {
       isRunning = true;

       this.currentScene = new Scene();
    };

    public void run() {
        long lastTime = System.nanoTime();
        while (isRunning) {
            long newTime = System.nanoTime();
            long frameTime = newTime - lastTime;
            lastTime = newTime;

            while( frameTime > 0.0 ) {
                double deltaTime = Math.min(frameTime, TARGET_FRAME_TIME);
                frameTime -= deltaTime;
                this.currentScene.update(deltaTime);
            }

            try {
                Thread.sleep(10);
            } catch(Exception e) {}
        }
    }
}
