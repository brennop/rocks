import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Transform {
    private AffineTransform affineTransform;
    private int size;

    public Transform(int x, int y, double rotation, int size) {
        this.size = size;
        affineTransform = new AffineTransform();
        this.affineTransform.translate(x, y);
        this.affineTransform.rotate(rotation);
    }

    public Point2D getPosition() {
        return new Point((int) affineTransform.getTranslateX(), (int) affineTransform.getTranslateY());
    }

    public void rotate(double theta){
       this.affineTransform.rotate(theta, this.size /2 , this.size / 2);
    }

    public AffineTransform getTransform() {
        return affineTransform;
    }
}
