import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class GameOverScene extends Scene {
    private final ArrayList<Integer> highscores = new ArrayList();

    @Override
    protected void start() {
        Game.getKeyListener().bindKey("SPACE", 32);

        try {
            File file = new File("./highscores.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader buffRead = new BufferedReader(new FileReader("./highscores.txt"));
            String line = buffRead.readLine();
            if (line != null) {
                for (String n : line.split(",")) {
                    highscores.add(Integer.parseInt(n));
                }
            }
            buffRead.close();

            BufferedWriter buffWriter = new BufferedWriter(new FileWriter("./highscores.txt"));
            highscores.add(Game.getScore());
            Collections.sort(highscores, Collections.reverseOrder());

            highscores.subList(Math.min(highscores.size(), 5), highscores.size()).clear();

            buffWriter.write(highscores
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")));
            buffWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        if(Game.getKeyListener().isKeyPressed("SPACE")) Game.startGame();
    }

    @Override
    public void draw(Graphics g, JPanel panel) {
        Graphics2D g2d = (Graphics2D) g;

        drawCentered(g2d, "Game Over", 40, 150);
        drawCentered(g2d, "Score: " + Game.getScore(), 10, 180);
        drawCentered(g2d, "Highscores: ", 20, 200);

        int index = 220;
        for (Integer i : highscores) {
            drawCentered(g2d, String.valueOf(i), 10, index);
            index += 20;
        }

        drawCentered(g2d, "Aperte ESPAÇO para começar de novo.", 15, 350);
    }

    private void drawCentered(Graphics2D g2d, String text, int size, int y) {
        Font font = new Font("sans-serif", Font.BOLD, size);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();

        int x = ((Game.getCurrentWidth() - fm.stringWidth(text)) / 2);

        g2d.setColor(new Color(240, 240, 240));

        g2d.drawString(text, x, y);
    }
}
