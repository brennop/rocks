import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Scene extends JPanel {
    private ArrayList<Entity> entities = new ArrayList();

    public Scene() {
        this.start();
    }

    private void start() {
        setPreferredSize(new Dimension(640, 480));
        setFocusable(true);
        requestFocus();
        setBackground(new Color(0, 0, 0));

        // teste entidade
        Entity entity = new Entity(
                50,
                50,
                0.0,
                0.0f,
                this
        );

        entities.add(entity);

        Entity entity2 = new Entity(
                100,
                100,
                0.0,
                0.0f,
                this
        );

        entities.add(entity2);

        Entity entity3 = new Entity(
                150,
                150,
                0.0,
                0.0f,
                this
        );

        entities.add(entity3);
    }

    public void update(double dt) {
        for (Entity entity: this.entities) {
            entity.update(dt);
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
