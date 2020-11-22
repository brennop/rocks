import javax.swing.*;
import java.awt.geom.Point2D;

public class Asteroid extends Entity {
    public Asteroid(Scene scene) {
        super((int) (Math.random() * scene.getWidth()),
                (int) (Math.random() * scene.getHeight()),
                Math.toRadians(Math.random() * 180),
                40, 0.1f, "asteroid", scene);
    }

    @Override
    public void update(double dt) {
        this.transform.translate(this.speed * dt, this.speed * dt);

        // Se sair da tela deve ser destruido
        Point2D position = this.transform.getPosition();
        if (position.getX() > scene.getWidth() + size ||
                position.getX() < 0 - size ||
                position.getY() > scene.getHeight() + size ||
                position.getY() < 0 - size) {
            this.destroy();
        }
    }

    @Override
    public void onCollision(Entity entity) {
    }
}
