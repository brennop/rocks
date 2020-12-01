import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Asteroid extends Entity {
    public Asteroid( Scene scene) {
        super(new AffineTransform(), 36, 0.1f, "asteroid", scene);
    }

    /*
     * No início, deve receber uma posição aleatória, e uma
     * direção aleatória
     */
    @Override
    public void start() {
        this.transform.translate((int) (Math.random() * Game.getCurrentWidth()), (int) (Math.random() * Game.getCurrentHeight()));
        this.transform.rotate(Math.random() * Math.PI);
    }

    @Override
    public void update(double dt) {
        // Move na direção escolhida
        this.transform.translate(this.speed * dt, this.speed * dt);

        // Se sair da tela deve ser destruido
        if (this.transform.getTranslateX() > Game.getCurrentWidth() + size ||
                this.transform.getTranslateX() < -size ||
                this.transform.getTranslateY() > Game.getCurrentHeight() + size ||
                this.transform.getTranslateY() < -size) {
            this.destroy();
        }
    }

    @Override
    public void onCollision(Entity entity) {
        // Se colidir com uma _bala_, deve ser destruído
        if (entity instanceof Bullet) {
            this.destroy();
        }
    }
}
