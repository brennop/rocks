import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Scene {
    protected ArrayList<Entity> entities = new ArrayList<Entity>();
    private boolean isFirstIteration = true;

    protected abstract void start();

    public void update(double dt) {
        if (isFirstIteration) {
            this.start();
            this.isFirstIteration = false;
            return;
        }
        for (Entity entity : new ArrayList<Entity>(entities)) {
            entity.update(dt);
        }

        for (int i = 0; i < entities.size(); i++) {
            Entity firstEntity = entities.get(i);
            for (int j = 0; j < entities.size(); j++) {
                Entity secondEntity = entities.get(j);
                if (firstEntity != secondEntity) {
                    double distance = firstEntity.getPosition().distanceSq(secondEntity.getPosition());
                    double radius = firstEntity.size / 2.0 + secondEntity.size / 2.0;
                    if (distance < radius * radius) {
                        firstEntity.onCollision(secondEntity);
                        secondEntity.onCollision(firstEntity);
                    }
                }
            }
        }
    }

    public abstract void draw(Graphics g, JPanel panel);

    protected void instantiate(Entity entity) {
        this.entities.add(entity);
    }

    public void remove(Entity entity) {
        // adiciona a lista para remoção ao final do update
        this.entities.remove(entity);
    }

}
