import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Scene extends JPanel {
    private ArrayList<Entity> entities = new ArrayList();
    private double time;

    public Scene() {
        this.start();
    }

    protected void start() {
        setPreferredSize(new Dimension(640, 480));
        setFocusable(true);
        requestFocus();
        setBackground(new Color(0, 0, 0));
    }

    public void update(double dt) {
        time += dt;
        for (Entity entity: this.entities) {
            entity.update(dt, time);
        }
        this.repaint();
    }

    public void draw(Graphics g) {
        for (Entity entity: this.entities) {
            entity.draw(g);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }
}
