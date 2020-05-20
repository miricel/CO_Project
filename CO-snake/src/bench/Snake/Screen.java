package bench.Snake;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Screen extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 520, HEIGHT = 520;
    public static final int max = (WIDTH*39)/400 +1;
    private final Snake fixedsnake;
    private final Snake floatingsnake;
    private final Snake simplesnake;

    private Thread thread;
    private boolean running = false;
    private Random r;

    private ArrayList<Apple> apples;
    private ArrayList<BodyPart> snake1;
    private ArrayList<BodyPart> snake2;
    private ArrayList<BodyPart> snake3;

    public Screen() {
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JFrame frame = new JFrame();

        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        r = new Random();

        apples = new ArrayList<Apple>();

        fixedsnake = new Snake(this, new Color(130,110,150),max,apples);
        floatingsnake = new Snake(this,new Color(100,150,100),max,apples);
        simplesnake = new Snake(this,new Color(150,100,100),max,apples);

        start();
    }



    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(new Color(150,140,150));
        for (int i = 0; i < WIDTH / 10; i++) {
            g.drawLine(i * 10, 0, i * 10, HEIGHT);
        }
        for (int i = 0; i < HEIGHT / 10; i++) {
            g.drawLine(0, i * 10, WIDTH, i * 10);
        }

        snake1 = fixedsnake.getBody();
        snake2 = floatingsnake.getBody();
        snake3 = simplesnake.getBody();
        for (int i = 0; i < snake1.size(); i++) {
            snake1.get(i).draw(g);
        }
        for (int i = 0; i < snake2.size(); i++) {
            snake2.get(i).draw(g);
        }
        for (int i = 0; i < snake3.size(); i++) {
            snake3.get(i).draw(g);
        }

        for(int i = 0; i < apples.size(); i++) {
            apples.get(i).draw(g);
        }

    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run() {
        while (running) {

          fixedsnake.tick();
          floatingsnake.tick();
          simplesnake.tick();
          repaint();
        }
    }

    public Snake getFixedsnake() {
        return fixedsnake;
    }

    public Snake getFloatingsnake() {
        return floatingsnake;
    }

    public Snake getSimplesnake() {
        return simplesnake;
    }

    //0 - right
    //1 - left
    //2 - up
    //3 - down
}