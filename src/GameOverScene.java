import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;

public class GameOverScene extends Scene {

    @Override
    protected void start() {

    }

    @Override
    public void draw(Graphics g, JPanel panel) {
        String gameOver = "Game Over";
        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("sans-serif", Font.BOLD, 48);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();

        int x = ((Game.getCurrentWidth() - fm.stringWidth(gameOver)) / 2);

        g2d.setColor(new Color(240, 240, 240));
        g2d.drawString(gameOver, x, 200);

    }
}
