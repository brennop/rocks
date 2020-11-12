import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Entity {
    private int x;
    private int y;
    private double rotation;
    private float speed;
    private Scene scene;
    private BufferedImage texture;

    public Entity(int x, int y, double rotation, float speed, Scene scene) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.speed = speed;
        this.scene = scene;

        try {
            texture = ImageIO.read(new File("assets/ship.png"));
        } catch(Exception exception) {
            assert false;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(texture, x, y, this.scene);
    }

    public void update(double dt) {
    }

    public void destroy() {
        this.scene.remove(this);
    }

    public void onCollision(Entity entity) {
    }
}
