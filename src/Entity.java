import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class Entity {
    protected final int size;
    protected float speed;
    protected AffineTransform transform;
    protected final Scene scene;
    private BufferedImage texture;

    public Entity(AffineTransform transform, int size, float speed, String filename, Scene scene) {
        this.speed = speed;
        this.scene = scene;
        this.size = size;
        this.transform = transform;

        // Lê o arquivo da sua imagem
        try {
            texture = ImageIO.read(new File("assets/" + filename + ".png"));
        } catch (Exception exception) {
            assert false;
        }

        this.start();
    }

    /*
     * Métodos abstratos da entidade
     */
    public abstract void update(double dt);
    public abstract void start();
    public abstract void onCollision(Entity entity);

    // Desenha a entidade no centro da sua imagem
    public void draw(Graphics g, JPanel panel) {
        Graphics2D g2d = (Graphics2D) g; // transforma o Graphics em um Graphics2D
        this.transform.translate(-this.size/2.0, -this.size/2.0);
        g2d.drawImage(this.texture, this.transform, panel); // desenha a imagem na tela usando o transform
        this.transform.translate(this.size/2.0, this.size/2.0);
    }

    // Remove a entidade da cena
    public void destroy() {
        this.scene.remove(this);
    }

    // Obtém a posição atual no sistema de coordenadas
    public Point2D getPosition() {
        return new Point((int) transform.getTranslateX(), (int) transform.getTranslateY());
    }
}
