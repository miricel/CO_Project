package bench.Snake;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Screen extends JPanel implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 520, HEIGHT = 520;
    public static final int max = (WIDTH*39)/400 +1;

    private Thread thread;
    private boolean running = false;

    private BodyPart b;
    private BodyPart b2;
    private ArrayList<BodyPart> snake;
    private ArrayList<BodyPart> snake2;

    private BodyPart bb;


    private Apple apple;
    private ArrayList<Apple> apples;

    private Color color1 = new Color(130,120,150);
    private Color color2 = new Color(100,150,100);

    private Random r;

    private int xCoor = 10, yCoor = 10;
    private int xCoor2 = 10, yCoor2 = 10;
    private int size = 5;
    private int size2 = 5;

    private boolean right = true, left = false, up = false, down =false;
    private boolean right2 = true, left2 = false, up2 = false, down2 =false;

    private int ticks = 0;

    public Screen() {
        setFocusable(true);

        addKeyListener(this);
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

        snake = new ArrayList<BodyPart>();
        snake2 = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();

        start();
    }

    public void tick() {
        if (snake.size() == 0) {
            b = new BodyPart(xCoor, yCoor, 10,color1);
            snake.add(b);
        }

        if (snake2.size() == 0) {
            b2 = new BodyPart(xCoor2, yCoor2, 10,color2);
            snake2.add(b2);
        }

        while(apples.size() < 40) {
            int xCoor = r.nextInt(max);
            int yCoor = r.nextInt(max);

            apple = new Apple(xCoor, yCoor, 10);
            apples.add(apple);
        }

        for(int i = 0; i < apples.size(); i++) {
            if(xCoor == apples.get(i).getxCoor() &&
                    yCoor == apples.get(i).getyCoor()) {
                size++;
                apples.remove(i);
                i++;
            }
        }

        for(int i = 0; i < apples.size(); i++) {
            if(xCoor2 == apples.get(i).getxCoor() &&
                    yCoor2 == apples.get(i).getyCoor()) {
                size2++;
                apples.remove(i);
                i++;
            }
        }

      /*  for(int i =0; i < snake.size(); i++) {
            if(xCoor == snake.get(i).getxCoor() &&
                    yCoor == snake.get(i).getyCoor()) {
                if(i != snake.size() - 1) {
                    System.out.println("Stop!");
                    stop();
                }
            }
        }
*/
        if(xCoor < 0 ) {
            goUp(1);
            xCoor ++;
        }
        else if(xCoor > max ) {
            goDown(1);
            xCoor --;
        }
        else if(yCoor < 0 ) {
            goRight(1);
            yCoor ++;
        }
        else if (yCoor > max) {
            goLeft(1);
            yCoor --;

            //stop();
        }

        if(xCoor2 < 0 ) {
            goUp(2);
            xCoor2 ++;
        }
        else if(xCoor2 > max ) {
            goDown(2);
            xCoor2 --;
        }
        else if(yCoor2 < 0 ) {
            goRight(2);
            yCoor2 ++;
        }
        else if (yCoor2 > max) {
            goLeft(2);
            yCoor2 --;

            //stop();
        }


        ticks++;

        if(ticks > 250000) {
            if(right) xCoor++;
            if(left) xCoor--;
            if(up) yCoor--;
            if(down) yCoor++;

            if(right2) xCoor2++;
            if(left2) xCoor2--;
            if(up2) yCoor2--;
            if(down2) yCoor2++;

            ticks = 0;

            b = new BodyPart(xCoor, yCoor, 10,color1);
            snake.add(b);

            b2 = new BodyPart(xCoor2,yCoor2,10,color2);
            snake2.add(b2);

            if(snake.size() > size) {
                snake.remove(0);
            }
            if(snake2.size() > size) {
                snake2.remove(0);
            }
        }

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

        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(g);
        }

        for(int i=0; i < snake2.size(); i++){
            snake2.get(i).draw(g);
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

          tick();
          repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT && !left) { // go right
           goRight(1);
        }
        if(key == KeyEvent.VK_LEFT && !right) {
           goLeft(1);
        }
        if(key == KeyEvent.VK_UP && !down) {
            goUp(1);
        }
        if(key == KeyEvent.VK_DOWN && !up) {
           goDown(1);
        }
    }

    //0 - right
    //1 - left
    //2 - up
    //3 - down

    public void move1(int direction, int index){
        if(direction == 0 && !left) { // go right
            goRight(index);
        }
        if(direction == 1 && !right) {
            goLeft(index);
        }
        if(direction == 2 && !down) {
            goUp(index);
        }
        if(direction == 3 && !up) {
            goDown(index);
        }
    }

    public void move2(int direction, int index){
        if(direction == 0 && !left2) { // go right
            goRight(index);
        }
        if(direction == 1 && !right2) {
            goLeft(index);
        }
        if(direction == 2 && !down2) {
            goUp(index);
        }
        if(direction == 3 && !up2) {
            goDown(index);
        }
    }

    private void goDown(int index) {
       if(index == 1){
           left = false;
           right = false;
           down = true;
       }else {
           left2 = false;
           right2 = false;
           down2 = true;
       }
    }

    private void goUp(int index) {
        if(index == 1){
            left = false;
            right = false;
            up = true;
        }else{
            left2 = false;
            right2 = false;
            up2 = true;
        }
    }

    private void goLeft(int index) {
      if(index == 1){
          up = false;
          down = false;
          left = true;
      }else {
          up2 = false;
          down2 = false;
          left2 = true;
      }
    }

    private void goRight(int index) {
       if(index == 1){
           up = false;
           down = false;
           right = true;
       }else {
           up2 = false;
           down2 = false;
           right2 = true;
       }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }
    public void keyTyped(KeyEvent arg0) {
    }
}