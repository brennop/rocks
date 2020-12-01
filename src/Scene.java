import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Scene implements Updatable {
    protected ArrayList<Entity> entities = new ArrayList<Entity>();
    private boolean isFirstIteration = true;

    public void update(double dt) {
        // Na primeira iteração, deve chamar o 
        // método start
        if (isFirstIteration) {
            this.start();
            this.isFirstIteration = false;
            return;
        }

        // atualiza todas as entidades
        // cria uma cópia da lista de entidades
        // para evitar modificações concorrentes
        for (Entity entity : new ArrayList<Entity>(entities)) {
            entity.update(dt);
        }

        // Checa se ocorreu colisões entre todas as entidades
        // Uma colisão ocorre quando a distância entre as entidades
        // é menor que a soma de seus raios (tamanho / 2.0)
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
        this.entities.remove(entity);
    }

}
