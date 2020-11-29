import javafx.scene.transform.Affine;

import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Player extends Entity {
    public Player(Scene scene) {
        super(
                (int) 320,
                (int) 240,
                90.0,
                50,
                0.0f,
                "ship",
                scene
        );

        System.out.println((int) (Math.random() * scene.getWidth()));
        System.out.println((int) (Math.random() * scene.getHeight()));
        this.scene.addKeyListener(new KeyListener(this));
    }

    public void shoot() {
        System.out.println("ainda nao implementado");
    }

    public void updateSpeed(float speed) {
        this.speed += speed;
    }

    @Override
    public void update(double dt) {
        super.update(dt);

        this.transform.translate(this.speed * dt, this.speed * dt);

        // se sair pela borda da tela vai para o outro lado
        Point2D position = this.transform.getPosition();
        if (position.getX() > scene.getWidth()) {
            this.resetPosition(0, (int) position.getY());
        } else if (position.getX() < 0) {
            this.resetPosition((int) scene.getWidth(), (int) position.getY());
        }

        if (position.getY() > scene.getHeight()) {
            this.resetPosition((int) position.getX(), 0);
        } else if (position.getY() < 0) {
            this.resetPosition((int) position.getX(), (int) scene.getHeight());
        }
    }
}
