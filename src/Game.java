import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    private static final KeyListener keyListener = new KeyListener();
    private static int score;
    private static Scene currentScene;

    private final double TARGET_FPS = 60;
    private final double TARGET_FRAME_TIME = 1000000000 / TARGET_FPS;

    private static final int width = 640;
    private static final int height = 480;

    // precisamos de uma thread para fazer o loop esperar um período
    private Thread thread;

    public Game() {
        // Define informações para a tela
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        setBackground(new Color(0, 0, 0));

        // Adiciona o listener do teclado
        this.addKeyListener(keyListener);

        // Coloca a cena inicial como a _cena do jogo_
        Game.currentScene = new GameScene();
    }

    // Incrementa o _score_
    public static void increaseScore(int score) {
        Game.score += score;
    }

    // Inicia o Jogo
    public static void startGame() {
        setScore(0);
        setCurrentScene(new GameScene());
    }

    // Vai para a tela de *fim do jogo*
    public static void gameOver() {
        setCurrentScene(new GameOverScene());
    }

    // Loop de Jogo / Loop Principal
    public void run() {
        long lastTime = System.nanoTime(); // tempo inicial
        while (true) {
            long newTime = System.nanoTime();
            long frameTime = newTime - lastTime; // tempo entre uma iteração
            lastTime = newTime;

            while (frameTime > 0.0) {
                double deltaTime = Math.min(frameTime, TARGET_FRAME_TIME);
                currentScene.update(deltaTime / 1000000); // atualiza a cena atual
                frameTime -= deltaTime;

                // causa um rerenderização
                this.repaint();
            }

            // delay intencial para desacelerar a simulação
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

    /*
     *   getters e setters
     */

    public static KeyListener getKeyListener() {
        return keyListener;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Game.score = score;
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

}
