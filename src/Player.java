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
        // Coloca o player no centro da tela no início
        this.transform.translate(Game.getCurrentWidth() / 2.0 , Game.getCurrentHeight() / 2.0);
    }

    @Override
    public void update(double dt) {
        KeyListener kl = Game.getKeyListener();

        // Ao pressionar espação, realiza um disparo
        if (kl.isKeyPressed("SPACE")) this.shoot();

        // Incrementa a velocidade angular
        if (kl.isKeyPressed("LEFT")) this.angularVelocity -= turnSpeed * dt;
        if (kl.isKeyPressed("RIGHT")) this.angularVelocity += turnSpeed * dt;

        // Realiza a rotação baseado na rotação angular
        this.transform.rotate(this.angularVelocity * dt);

        // Aumenta a velocidade caso a tecla W seja pressionada
        if (kl.isKeyPressed("UP")) this.velocity += this.speed;

        // Move o jogador baseado na sua velocidade
        this.transform.translate(0, -this.velocity * dt);

        // Reduz a velocidade linear e angular do player 
        // baseado em uma constante (damping)
        this.velocity -= this.velocity * linearDamping * dt * 0.0001;
        this.angularVelocity -= this.angularVelocity * angularDamping * dt * 0.0001;

        // atualiza o tempo para o disparo
        this.timeToShoot -= dt;

        // Se sair da tela deve ocorrer game over
        if (this.transform.getTranslateX() > Game.getCurrentWidth() + size ||
                this.transform.getTranslateX() < -size ||
                this.transform.getTranslateY() > Game.getCurrentHeight() + size ||
                this.transform.getTranslateY() < -size) {
            Game.gameOver();
        }

    }

    public void shoot() {
        // se ainda não é possível atirar, para a função
        if (this.timeToShoot > 0) return;

        // copia o transform do jogador para a bala
        AffineTransform bulletTransform = new AffineTransform(this.transform);

        // inverte a direção da bala
        bulletTransform.rotate(Math.PI);

        // move a bala para o bico da nava
        bulletTransform.translate(0, this.size/2.0);

        // instancia uma bala na cena
        this.scene.instantiate(new Bullet(bulletTransform, this.scene));

        // reinicia o tempo de disparo
        this.timeToShoot = bulletInterval;
    }

    @Override
    public void onCollision(Entity entity) {
        // Se colidir com um asteróide, ocorre um Game Over
        if (entity instanceof Asteroid)
            Game.gameOver();
    }
}
