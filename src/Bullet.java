import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Bullet extends Entity {
    public Bullet(AffineTransform transform, Scene scene) {
        super(transform, 2, 0.4f, "bullet00", scene);
    }

    @Override
    public void update(double dt) {
        this.transform.translate(0,this.speed * dt);

        // Se sair da tela deve ser destruido
        if (this.transform.getTranslateX() > Game.getCurrentWidth() + size ||
                this.transform.getTranslateX() < -size ||
                this.transform.getTranslateY() > Game.getCurrentHeight() + size ||
                this.transform.getTranslateY() < -size) {
            this.destroy();
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void onCollision(Entity entity) {
        if (entity instanceof Player || entity instanceof Bullet) {
            return;
        }
        this.destroy();
    }
}
