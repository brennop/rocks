import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    private static final KeyListener keyListener = new KeyListener();
    private static int score;
    private static Scene currentScene;
    private final double TARGET_FPS = 60;
    private final double TARGET_FRAME_TIME = 1000000000 / TARGET_FPS;
    // precisamos de uma thread para fazer o loop esperar um período

    private static final int width = 640;
    private static final int height = 480;

    private Thread thread;
    public Game() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        setBackground(new Color(0, 0, 0));

        this.addKeyListener(keyListener);

        Game.currentScene = new GameScene();
        currentScene.start();
    }

    public static KeyListener getKeyListener() {
        return keyListener;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Game.score = score;
    }

    public static void gameOver() {
        setCurrentScene(new GameOverScene());
        currentScene.start();
    }

    public static void setCurrentScene(Scene scene) {
        Game.currentScene = scene;
    }

    public static int getCurrentWidth() {
        return width;
    }

    public static int getCurrentHeight() {
        return height;
    }

    public void run() {
        long lastTime = System.nanoTime();
        while (true) {
            long newTime = System.nanoTime();
            long frameTime = newTime - lastTime;
            lastTime = newTime;

            while (frameTime > 0.0) {
                double deltaTime = Math.min(frameTime, TARGET_FRAME_TIME);
                currentScene.update(deltaTime / 1000000);
                frameTime -= deltaTime;

                this.repaint();
            }

            try {
                Thread.sleep(1);
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentScene.draw(g, this);
    }
}
