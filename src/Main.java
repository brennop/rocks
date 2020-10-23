import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Game.info().setName("rocks");
        Game.info().setSubTitle("");
        Game.info().setVersion("v0.0.1");
        Game.init(args);
        Game.start();
    }
}
