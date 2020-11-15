import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Transform {
    private final AffineTransform affineTransform;
    private final int size;
    private double rotation;

    public Transform(int x, int y, double rotation, int size) {
        this.size = size;
        affineTransform = new AffineTransform();
        this.affineTransform.translate(x, y);
        this.affineTransform.rotate(rotation);
        this.rotation = rotation;
    }

    public Point2D getPosition() {
        return new Point((int) affineTransform.getTranslateX(), (int) affineTransform.getTranslateY());
    }

    public void rotate(double theta) {
        this.rotation = rotation;
        this.affineTransform.rotate(theta, this.size / 2, this.size / 2);
    }

    public void translate(double x, double y) {
        this.affineTransform.translate(x, y);
    }

    // Aplica translação respeitando a rotação
    // https://pt.wikipedia.org/wiki/Matriz_de_rota%C3%A7%C3%A3o
    public void move(double dx, double dy) {
        double cos = Math.cos(this.rotation);
        double sin = Math.sin(this.rotation);
        double rotatedX =  dx * cos - dy * sin;
        double rotatedY = dx * sin + dy * cos;
        this.translate(rotatedX, rotatedY);
    }

    public AffineTransform getTransform() {
        return affineTransform;
    }
}
