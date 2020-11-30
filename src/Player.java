import java.awt.geom.Point2D;

public class Player extends Entity {
    private final float turnSpeed = 0.00008f;
    private final float linearDamping = 50;
    private final float angularDamping = 100;
    private final Vector velocity = new Vector(0, 0);
    private int timeToShoot = 0;
    private float angularVelocity = 0;

    public Player(Scene scene) {
        super(
                320,
                240,
                0.0,
                32,
                0.0015f,
                "ship",
                scene
        );
    }

    public void shoot() {
        if (this.timeToShoot > 0) {
            return;
        }
        Point2D position = this.transform.getPosition();
        double rotation = Math.atan2(
                this.transform.affineTransform.getShearY(),
                this.transform.affineTransform.getScaleY()
        );

        this.scene.instantiate(new Bullet(
                scene,
                (int) position.getX(),
                (int) position.getY(),
                rotation - Math.PI + 0.75));

        this.timeToShoot = 100;
    }

    public void updateSpeed(float speed) {
        this.speed += speed;
    }

    @Override
    public void update(double dt) {
        KeyListener kl = Game.getKeyListener();

        if (kl.isKeyPressed("SPACE")) this.shoot();

        // Realiza a rotação
        if (kl.isKeyPressed("LEFT")) this.angularVelocity -= turnSpeed * dt;
        if (kl.isKeyPressed("RIGHT")) this.angularVelocity += turnSpeed * dt;

        this.transform.rotate(this.angularVelocity * dt);

        //
        if (kl.isKeyPressed("UP")) {
            this.velocity.add(
                    Math.sin(this.transform.getRotation()) * this.speed,
                    Math.cos(this.transform.getRotation()) * this.speed
            );
        }

        this.transform.translate(-this.velocity.getX() * dt, -this.velocity.getY() * dt);

        // se sair pela borda da tela vai para o outro lado
        /*
        Point2D position = this.transform.getPosition();
        if (position.getX() > scene.getWidth()) {
            this.resetPosition(0, (int) position.getY());
        } else if (position.getX() < 0) {
            this.resetPosition(scene.getWidth(), (int) position.getY());
        }

        if (position.getY() > scene.getHeight()) {
            this.resetPosition((int) position.getX(), 0);
        } else if (position.getY() < 0) {
            this.resetPosition((int) position.getX(), scene.getHeight());
        }*/

        this.velocity.add(-this.velocity.getX() * linearDamping * dt * 0.0001, -this.velocity.getY() * linearDamping * dt * 0.0001);
        this.angularVelocity -= this.angularVelocity * angularDamping * dt * 0.0001;

        this.timeToShoot -= dt;
    }

    @Override
    public void onCollision(Entity entity) {
        if (entity instanceof Asteroid)
            Game.gameOver();
    }
}
