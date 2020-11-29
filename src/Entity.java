import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class Entity {
    protected final int size;
    protected float speed;
    protected Transform transform;
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

    public abstract void update(double dt);

    public abstract void onCollision(Entity entity);

    public void resetPosition(int x, int y) {
        double rotation = Math.atan2(
                this.transform.affineTransform.getShearY(),
                this.transform.affineTransform.getScaleY()
        );
        this.transform = new Transform(x, y, rotation, this.size);
    }

    public void draw(Graphics g, JPanel panel) {
        Graphics2D g2d = (Graphics2D) g; // transforma o Graphics em um Graphics2D
        g2d.drawImage(this.texture, this.transform.getTransform(), panel); // desenha a imagem na tela usando o transform
    }

    public void destroy() {
        this.scene.remove(this);
    }
}
