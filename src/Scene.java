import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Scene {
    protected ArrayList<Entity> entities = new ArrayList<Entity>();
    private ArrayList<Entity> toRemove = new ArrayList<Entity>();
    private ArrayList<Entity> toAdd = new ArrayList<Entity>();

    protected abstract void start();

    public void update(double dt) {
        for (Entity entity: new ArrayList<Entity>(entities) ) {
            entity.update(dt);
        }

        for (Entity firstEntity : new ArrayList<Entity>(entities)) {
            for(Entity secondEntity : new ArrayList<Entity>(entities)) {
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
