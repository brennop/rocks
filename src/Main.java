import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        setTitle("rocks v0.0.1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game game = new Game();
        setContentPane(game);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
        game.run();
    }

    public static void main(String[] args) {
        new Main();
    }
}
