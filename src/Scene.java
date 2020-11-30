import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Scene {
    protected ArrayList<Entity> entities = new ArrayList<Entity>();
    private ArrayList<Entity> toRemove = new ArrayList<Entity>();

    protected abstract void start();

    public void update(double dt) {
        // reseta a lista de remoção
        this.toRemove = new ArrayList();

        for (Entity entity: this.entities) {
            entity.update(dt);
        }

        for (Entity firstEntity : entities) {
            for(Entity secondEntity : entities) {
                if(firstEntity != secondEntity) {
                    double distance = firstEntity.transform.getPosition().distanceSq(secondEntity.transform.getPosition());
                    double radius = firstEntity.size / 2.0 + secondEntity.size / 2.0;
                    if(distance < radius * radius) {
                        firstEntity.onCollision(secondEntity);
                        secondEntity.onCollision(firstEntity);
                    }
                }
            }
        }

        // remove os items depois da iteração
        // para evitar um ConcurrentModificationException
        entities.removeAll(toRemove);

    }

    public abstract void draw(Graphics g, JPanel panel);

    protected void instantiate(Entity entity) {
        this.entities.add(entity);
    }

    public void remove(Entity entity) {
        // adiciona a lista para remoção ao final do update
        this.toRemove.add(entity);
    }

}
