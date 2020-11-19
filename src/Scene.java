import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Scene extends JPanel {
    private ArrayList<Entity> entities = new ArrayList();
    private ArrayList<Entity> toRemove = new ArrayList();

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
                Math.toRadians(30),
                32,
                0.0f,
                this
        );

        entities.add(entity);

        Entity entity2 = new Entity(
                100,
                100,
                Math.toRadians(60),
                32,
                0.0f,
                this
        );

        entities.add(entity2);

        Entity entity3 = new Entity(
                150,
                150,
                Math.toRadians(180),
                32,
                0.0f,
                this
        );

        entities.add(entity3);
    }

    public void update(double dt) {
        // reseta a lista de remoção
        this.toRemove = new ArrayList();

        for (Entity entity: this.entities) {
            entity.update(dt);
        }

        // remove os items depois da iteração
        // para evitar um ConcurrentModificationException
        entities.removeAll(toRemove);
        this.repaint();
    }

    public void draw(Graphics g) {
        for (Entity entity: this.entities) {
            entity.draw(g);
        }
    }

    public void remove(Entity entity) {
        // adiciona a lista para remoção ao final do update
        this.toRemove.add(entity);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }
}
