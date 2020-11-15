import javax.swing.*;
import java.awt.geom.Point2D;

public class Asteroid extends Entity {
    public Asteroid(JPanel game) {
        super((int) (Math.random() * game.getWidth()),
                (int) (Math.random() * game.getHeight()),
                Math.toRadians(Math.random() * 180),
                20, 0.1f, "asteroid", game);
    }

    @Override
    public void update(double dt) {
        super.update(dt);

        this.transform.translate(this.speed * dt, this.speed * dt);

        // Se sair da tela deve ser destruido
        Point2D position = this.transform.getPosition();
        if (position.getX() > game.getWidth() + size ||
                position.getX() < 0 - size ||
                position.getY() > game.getHeight() + size ||
                position.getY() < 0 - size) {
            this.destroy();
        }
    }
}
