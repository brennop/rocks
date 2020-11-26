public class GameScene extends Scene {
    private final double asteroidInterval = 1000;
    private double timeToNextAsteroid = 0;
    private Player player;

    @Override
    protected void start() {
        super.start();

        player = new Player(this);
        this.instantiate(player);
    }

    @Override
    public void update(double dt) {
        super.update(dt);

        // Cria um novo asteroid a cada asteroidInterval milisegundos
        if (timeToNextAsteroid <= 0) {
            this.instantiate(new Asteroid(this));
            timeToNextAsteroid = asteroidInterval;
        }

        timeToNextAsteroid -= dt;
    }
}
