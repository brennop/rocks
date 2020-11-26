import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
    private Player player;

    public KeyListener(Player player) {
        super();

        this.player = player;
    }
    @Override
    public void keyPressed(KeyEvent event) {
        int keyPressed = event.getKeyCode();
        if (keyPressed == 83) {
            this.player.updateSpeed(0.005f);
        } else if (keyPressed == 87) {
            this.player.updateSpeed(- 0.005f);
        } else if (keyPressed == 65) {
            this.player.transform.rotate(0.1f);
        } else if (keyPressed == 68) {
            this.player.transform.rotate(- 0.1f);
        } else if (keyPressed == 32) {
            this.player.shoot();
        }
    }
}
