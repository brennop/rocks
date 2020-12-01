import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyListener extends KeyAdapter {
    private final boolean[] keyState = new boolean[256];
    private final HashMap<String, Integer> keyBinds = new HashMap<String, Integer>();

    // Quando uma tecla for pressionada, altere 
    // o seu estado para true
    @Override
    public void keyPressed(KeyEvent event) {
        int keyPressed = event.getKeyCode();
        keyState[keyPressed] = true;
        event.consume();
    }

    // Quando uma tecla for soltada, altere 
    // o seu estado para false
    @Override
    public void keyReleased(KeyEvent event) {
        int keyPressed = event.getKeyCode();
        keyState[keyPressed] = false;
        event.consume();
    }

    // Obtém o estado atual de uma tecla
    public boolean isKeyPressed(String keyName) {
        return keyState[keyBinds.get(keyName)];
    }

    // Método para associar uma tecla a um nome
    public void bindKey(String name, int key) {
        keyBinds.put(name, key);
    }
}
