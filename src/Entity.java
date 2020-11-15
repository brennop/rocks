import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Entity {
    private final int size;
    protected final float speed;
    protected final Transform transform;
    private final JPanel game;
    private BufferedImage texture;

    public Entity(int x, int y, double rotation, int size, float speed, String filename, JPanel game) {
        this.speed = speed;
        this.game = game;
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
        g2d.drawImage(this.texture, this.transform.getTransform(), this.game); // desenha a imagem na tela usando o transform
    }

    public void update(double dt) {
    }

    public void destroy() {
    }

    public void onCollision(Entity entity) {
    }
}
