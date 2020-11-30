import javax.swing.*;
import java.awt.*;

public class GameScene extends Scene {
    private final double asteroidInterval = 1000.0;
    private double timeToNextAsteroid = 0;
    private Player player;

    @Override
    protected void start() {
        player = new Player(this);
        this.instantiate(player);

        Game.getKeyListener().bindKey("SPACE", 32);
        Game.getKeyListener().bindKey("UP", 87);
        Game.getKeyListener().bindKey("DOWN", 83);
        Game.getKeyListener().bindKey("LEFT", 65);
        Game.getKeyListener().bindKey("RIGHT", 68);
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

    @Override
    public void draw(Graphics g, JPanel panel) {
        for (Entity entity: this.entities) {
            entity.draw(g, panel);
        }
    }
}
