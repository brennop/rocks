import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class Transform {
    protected final AffineTransform affineTransform;
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

    public void setTransform(AffineTransform affineTransform) {
        affineTransform.setTransform(affineTransform);
    }

    public double getX() {
        return affineTransform.getTranslateX();
    }

    public double getY() {
        return affineTransform.getTranslateY();
    }

    public void rotate(double theta) {
        this.rotation = theta;
        this.affineTransform.rotate(theta, this.size / 2.0, this.size / 2.0);
    }

    public double getRotation() {
        return rotation;
    }

    public void translate(double dx, double dy) {
        this.affineTransform.translate(dx, dy);
    }

    public AffineTransform getTransform() {
        return affineTransform;
    }
}
