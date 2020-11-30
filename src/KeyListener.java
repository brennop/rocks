import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyListener extends KeyAdapter {
    private final boolean[] keyState = new boolean[256];
    private final HashMap<String, Integer> keyBinds = new HashMap<String, Integer>();

    @Override
    public void keyPressed(KeyEvent event) {
        int keyPressed = event.getKeyCode();
        keyState[keyPressed] = true;
        event.consume();
    }

    @Override
    public void keyReleased(KeyEvent event) {
        int keyPressed = event.getKeyCode();
        keyState[keyPressed] = false;
        event.consume();
    }

    public boolean isKeyPressed(String keyName) {
        return keyState[keyBinds.get(keyName)];
    }

    public void bindKey(String name, int key) {
        keyBinds.put(name, key);
    }
}
