import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Scene extends JPanel {
    private ArrayList<Entity> entities = new ArrayList();
    private ArrayList<Entity> toRemove = new ArrayList();
    protected KeyListener keyListener = new KeyListener();

    public Scene() {
        this.start();
    }

    protected void start() {
        setPreferredSize(new Dimension(640, 480));
        setFocusable(true);
        requestFocus();
        setBackground(new Color(0, 0, 0));

        this.addKeyListener(keyListener);
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
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

    protected void instantiate(Entity entity) {
        this.entities.add(entity);
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
