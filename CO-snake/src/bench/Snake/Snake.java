package bench.Snake;

import javax.swing.JFrame;

public class Snake {

    Screen  screen;
    public Snake() {

        JFrame frame = new JFrame();
        screen = new Screen();

        frame.add(screen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        new Snake();
    }
}