import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Entity {
    private final int size;
    private double rotation;
    private float speed;
    private Scene scene;
    private BufferedImage texture;
    private final Transform transform;

    public Entity(int x, int y, double rotation, int size, float speed, Scene scene) {
        this.rotation = rotation;
        this.speed = speed;
        this.scene = scene;
        this.size = size;

        this.transform = new Transform(x, y, rotation, size);

        try {
            texture = ImageIO.read(new File("assets/ship.png"));
        } catch (Exception exception) {
            assert false;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // transforma no Graphics em um Graphics2D
        g2d.drawImage(this.texture, this.transform.getTransform(), this.scene); // desenha a imagem na tela usando o transform
    }

    public void update(double dt) {
        this.transform.rotate(0.01 * dt);
    }

    public void destroy() {
        this.scene.remove(this);
    }

    public void onCollision(Entity entity) {
    }
}
