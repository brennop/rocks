import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    private final double TARGET_FPS = 60;
    private final double TARGET_FRAME_TIME = 1000000000 / TARGET_FPS;

    // precisamos de uma thread para fazer o loop esperar
    // o período
    private Thread thread;
    private Object ship;
    private boolean isRunning = false;

    public Game() {
       setPreferredSize(new Dimension(640, 480));
       setFocusable(true);
       requestFocus();
       setBackground(new Color(0, 0, 0));

       isRunning = true;
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
            }

            this.repaint();

            try {
                Thread.sleep(10);
            } catch(Exception e) {}
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
