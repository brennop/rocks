import javax.swing.*;
import java.awt.geom.Point2D;

public class Bullet extends Entity {
    public Bullet(Scene scene, int x, int y, double theta) {
        super(
                x,
                y,
                theta,
                20,
                0.2f,
                "bullet00",
                scene
        );
    }

    @Override
    public void update(double dt) {
        super.update(dt);

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
        if (entity instanceof Player || entity instanceof Bullet) {
            return;
        }
        this.destroy();
    }
}
