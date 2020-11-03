import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Object {
    private BufferedImage image;
    private final Game game;

    private int x;
    private int y;

    public Object(Game game) {
        this.game = game;
        try {
            image = ImageIO.read(new File("assets/ship.png"));
        } catch (IOException e) {}
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, game);
    }

    public void update(double dt) {
       this.x += 1 * dt;
       this.y += 1 * dt;
    }
}
