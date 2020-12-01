import javax.swing.*;
import java.awt.*;

public class GameScene extends Scene {
    private final double asteroidInterval = 600.0;
    private double timeToNextAsteroid = 0;
    private Player player;

    @Override
    public void start() {
        // Cria um novo Player na cena
        player = new Player(this);
        this.instantiate(player);

        // Associa as teclas a serem usadas a nomes mnemonicos
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

        // Atualiza o tempo para o próximo asteróides
        timeToNextAsteroid -= dt;
    }

    @Override
    public void draw(Graphics g, JPanel panel) {
        // Desenha todas as entidades presentes
        for (Entity entity: this.entities) {
            entity.draw(g, panel);
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(240, 240, 240));

        // Mostra o score atual
        g2d.drawString("Score: " + Game.getScore(), 10, 20);
    }
}
