import java.awt.geom.AffineTransform;

public class Player extends Entity {
    private final float turnSpeed = 0.00004f;
    private final float linearDamping = 50;
    private final float angularDamping = 100;
    private final float bulletInterval = 400;

    private float velocity = 0;
    private float timeToShoot = 0;
    private float angularVelocity = 0;

    public Player(Scene scene) {
        super(
                new AffineTransform(),
                32,
                0.001f,
                "ship",
                scene
        );
    }

    @Override
    public void start() {
        this.transform.translate(Game.getCurrentWidth() / 2.0 , Game.getCurrentHeight() / 2.0);
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
        if (kl.isKeyPressed("UP")) this.velocity += this.speed;

        this.transform.translate(0, -this.velocity * dt);

        this.velocity -= this.velocity * linearDamping * dt * 0.0001;
        this.angularVelocity -= this.angularVelocity * angularDamping * dt * 0.0001;

        this.timeToShoot -= dt;
    }

    public void shoot() {
        if (this.timeToShoot > 0) return;
        AffineTransform bulletTransform = new AffineTransform(this.transform);
        bulletTransform.rotate(Math.PI);
        bulletTransform.translate(0,this.size/2.0);
        this.scene.instantiate(new Bullet(bulletTransform, this.scene));
        this.timeToShoot = bulletInterval;
    }

    @Override
    public void onCollision(Entity entity) {
        if (entity instanceof Asteroid)
            Game.gameOver();
    }
}
