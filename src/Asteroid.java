import javax.swing.*;
import java.awt.geom.Point2D;

public class Asteroid extends Entity {
    public Asteroid(Scene scene) {
        super((int) (Math.random() * Game.getCurrentWidth()),
                (int) (Math.random() * Game.getCurrentHeight()),
                Math.toRadians(Math.random() * 180),
                40, 0.1f, "asteroid", scene);
    }

    @Override
    public void update(double dt) {
        this.transform.translate(this.speed * dt, this.speed * dt);

        // Se sair da tela deve ser destruido
        Point2D position = this.transform.getPosition();
        if (position.getX() > Game.getCurrentWidth() + size ||
                position.getX() < 0 - size ||
                position.getY() > Game.getCurrentHeight() + size ||
                position.getY() < 0 - size) {
            this.destroy();
        }
    }

    @Override
    public void onCollision(Entity entity) {
        if (entity instanceof Bullet) {
            this.destroy();
        }
    }
}
