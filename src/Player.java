import javafx.scene.transform.Affine;

import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Player extends Entity {
    private final float turnSpeed = 0.000005f;
    private float velocity = 0;
    private float angularVelocity = 0;

    public Player(Scene scene) {
        super(
                (int) 320,
                (int) 240,
                0.0,
                32,
                0.0001f,
                "ship",
                scene
        );

        System.out.println((int) (Math.random() * scene.getWidth()));
        System.out.println((int) (Math.random() * scene.getHeight()));
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
        KeyListener kl = scene.getKeyListener();

        // Realiza a rotação
        if(kl.isKeyPressed("LEFT")) this.angularVelocity -= turnSpeed * dt;
        if(kl.isKeyPressed("RIGHT")) this.angularVelocity += turnSpeed * dt;

        this.transform.rotate(this.angularVelocity * dt);

        //
        if(kl.isKeyPressed("UP")) this.velocity += this.speed * dt;
        if(kl.isKeyPressed("DOWN")) this.velocity -= this.speed * dt;

        this.transform.translate(0,-this.velocity * dt);

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

        this.velocity -= this.velocity * 0.001f * dt;
        this.angularVelocity -= this.angularVelocity * 0.001f * dt;
    }
}
