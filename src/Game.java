public class Game {
    private final double TARGET_FPS = 60;
    private final double TARGET_FRAME_TIME = 1000000000 / TARGET_FPS;
    private final Scene currentScene;
    // precisamos de uma thread para fazer o loop esperar
    // o período
    private Thread thread;
    private boolean isRunning = false;

    public Game() {
        isRunning = true;

        this.currentScene = new GameScene();
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void run() {
        long lastTime = System.nanoTime();
        while (isRunning) {
            long newTime = System.nanoTime();
            long frameTime = newTime - lastTime;
            lastTime = newTime;

            while (frameTime > 0.0) {
                double deltaTime = Math.min(frameTime, TARGET_FRAME_TIME);
                this.currentScene.update(deltaTime / 1000000);
                frameTime -= deltaTime;
            }

            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
        }
    }
}
