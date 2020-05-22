package bench.Snake;
import testbench.SnakeWithFixedPoint;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Screen extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 561, HEIGHT = 561;
    public static final int max = (WIDTH*39)/400 +1;
    private Snake fixedsnake = new Snake(this, new Color(120,110,150),max);
    private Snake floatingsnake = new Snake(this,new Color(100,150,100),max);

    static int n = 0;

    private boolean running = false;
    private Random r;

    private ArrayList<Apple> apples = new ArrayList<Apple>();
    private ArrayList<BodyPart> snake1 = new ArrayList<>();
    private ArrayList<BodyPart> snake2 = new ArrayList<>();

    public SnakeCount purple = new SnakeCount("Fixed Point: ");
    public SnakeCount green = new SnakeCount("Floating Point: ");

    public Screen() {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JFrame frame = new JFrame();
        JPanel contentpane = new JPanel();


        JPanel panel = new JPanel();
        panel.setBounds(WIDTH+54,0,300,HEIGHT + 160);
        panel.setLayout(null);
        panel.setBackground(Color.black);
        contentpane.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setBounds(300,200,WIDTH+400, HEIGHT+160);
        frame.setBackground(Color.black);
        frame.setLayout(null);
        frame.add(contentpane);
        contentpane.setLayout(null);
        contentpane.setBounds(0,0,1000,800);
        contentpane.setBackground(Color.black);

        JPanel black = new JPanel();
        black.setBounds(50,50,WIDTH+8,HEIGHT+10);
        black.add(this);
        black.setBackground(Color.black);
        contentpane.add(black);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(80,350,150,40);
        panel.add(cancel);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SnakeWithFixedPoint.cancel();
                stop();
            }
        });

        purple.setBounds(77,200,190,50);
        purple.setForeground(new Color(120,110,150));
        green.setBounds(0,275,300,50);
        green.setForeground(new Color(100,150,100));
        green.setBorder(new EmptyBorder(1,77,1,10));
        cancel.setBackground(Color.pink);
        cancel.setBorder(null);
        try {
            cancel.setFont(Font.createFont(Font.TRUETYPE_FONT, SnakeCount.class.getResourceAsStream("/DarkSeed.otf")).deriveFont(19f));
        } catch (FontFormatException ee) {
            ee.printStackTrace();
        } catch (IOException ee) {
            ee.printStackTrace();
        }
        panel.add(green);
        panel.add(purple);

        r = new Random();
        apples = Snake.getApples();

        start();
    }



    public synchronized void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(new Color (20,20,20));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(new Color(100,90,100));
        for (int i = 0; i <= WIDTH / 10; i++) {
            g.drawLine(i * 10, 0, i * 10, HEIGHT);
        }
        for (int i = 0; i <= HEIGHT / 10; i++) {
            g.drawLine(0, i * 10, WIDTH, i * 10);
        }

        snake1 = fixedsnake.getBody();
        snake2 = floatingsnake.getBody();
        for (int i = 0; i < snake1.size(); i++) {
            snake1.get(i).draw(g);
        }
        for (int i = 0; i < snake2.size(); i++) {
            snake2.get(i).draw(g);
        }

        for(int i = 0; i < apples.size(); i++) {
            apples.get(i).draw(g);
        }

    }

    public void start() {
        running = true;
        floatingsnake.start();
        fixedsnake.start();

    }

    public void stop() {

        fixedsnake.stop();
        floatingsnake.stop();

        try {
            fixedsnake.join();
            floatingsnake.join();

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Snake getFixedsnake() {
        return fixedsnake;
    }

    public Snake getFloatingsnake() {
        return floatingsnake;
    }

    public void addPurple(){
        purple.add();
    }

    public void addGreen(){
        green.add();
    }


    //0 - right
    //1 - left
    //2 - up
    //3 - down
}