import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class GameOverScene extends Scene {
    private final ArrayList<Integer> highscores = new ArrayList();

    @Override
    public void start() {
        // Associa o código 32 (barra de espaço)
        // ao nome "SPACE"
        Game.getKeyListener().bindKey("SPACE", 32);

            // Adiciona o score atual aos highscores
            highscores.add(Game.getScore());

        try {
            // Lê o arquivo de pontuações mais altas
            File file = new File("./highscores.txt");

            // Se o arquivo não existe, cria
            if (!file.exists()) {
                file.createNewFile();
            }

            // Cria um buffer para a leitura
            BufferedReader buffRead = new BufferedReader(new FileReader("./highscores.txt"));

            // Lê a primeira linha
            String line = buffRead.readLine();

            // A linha existir, separa entre as vírgulas ","
            // e guarda cada valor na lista highscores 
            if (line != null) {
                for (String n : line.split(",")) {
                    highscores.add(Integer.parseInt(n));
                }
            }

            // Fecha o buffer para evitar memory leaks
            buffRead.close();

            // Cria um buffer para a escrita
            BufferedWriter buffWriter = new BufferedWriter(new FileWriter("./highscores.txt"));

            // Ordena os highscores a partir do mais alto
            Collections.sort(highscores, Collections.reverseOrder());

            // Remove os scores que não estão entre os 5 maiores
            highscores.subList(Math.min(highscores.size(), 5), highscores.size()).clear();

            // Junta os highscores em uma String separada por vírgula
            // e salva no arquivo de pontuações
            buffWriter.write(highscores
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")));

            // Fecha o buffer para evitar memory leaks
            buffWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(double dt) {
        super.update(dt);

        // Se o usuário pressionar espaço, reiniciar o jogo
        if(Game.getKeyListener().isKeyPressed("SPACE")) Game.startGame();
    }

    @Override
    public void draw(Graphics g, JPanel panel) {
        Graphics2D g2d = (Graphics2D) g;

        // Desenha as informações principais
        drawCentered(g2d, "Game Over", 40, 150);
        drawCentered(g2d, "Score: " + Game.getScore(), 10, 180);
        drawCentered(g2d, "Highscores: ", 20, 200);

        // Desenha os highscores
        int index = 220;
        for (Integer i : highscores) {
            drawCentered(g2d, String.valueOf(i), 10, index);
            index += 20;
        }

        drawCentered(g2d, "Aperte ESPAÇO para começar de novo.", 15, 350);
    }

    // Método para desenhar um texto centrado na tela
    private void drawCentered(Graphics2D g2d, String text, int size, int y) {
        Font font = new Font("sans-serif", Font.BOLD, size);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();

        int x = ((Game.getCurrentWidth() - fm.stringWidth(text)) / 2);

        g2d.setColor(new Color(240, 240, 240));

        g2d.drawString(text, x, y);
    }
}
