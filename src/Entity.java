import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Entity {
    protected final int size;
    protected final float speed;
    protected final Transform transform;
    protected final Scene scene;
    private BufferedImage texture;

    public Entity(int x, int y, double rotation, int size, float speed, String filename, Scene scene) {
        this.speed = speed;
        this.scene = scene;
        this.size = size;

        this.transform = new Transform(x, y, rotation, size);

        try {
            texture = ImageIO.read(new File("assets/" + filename + ".png"));
        } catch (Exception exception) {
            assert false;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // transforma no Graphics em um Graphics2D
        g2d.drawImage(this.texture, this.transform.getTransform(), this.scene); // desenha a imagem na tela usando o transform
    }

    public void update(double dt) {
    }

    public void destroy() {
        this.scene.remove(this);
    }

    public void onCollision(Entity entity) {
    }
}
