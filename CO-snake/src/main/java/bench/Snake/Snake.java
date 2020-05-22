package bench.Snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Snake extends Thread {

    private final int max;
    private static Random r = new Random();
    private Screen  screen;
    private final static ArrayList<Apple> apples = new ArrayList<Apple>() ;
    private ArrayList<BodyPart> body = new ArrayList<BodyPart>();
    private BodyPart b;
    private static Apple apple;
    private int xCoor = r.nextInt(50), yCoor = r.nextInt(50);
    private int size = 15;
    private boolean right = true, left = false, up = false, down =false;
    private Color color;
    private boolean ok = false;

    private int ticks = 0;

    public Snake(Screen screen, Color color,int max) {
        this.color = color;
        this.screen = screen;
        this.max = max;

    }

    @Override
    public void run() {

        while(true){
            tick();
            screen.repaint();
        }
    }

    public void tick() {
        if (this.body.size() == 0) {
            b = new BodyPart(xCoor, yCoor, 10,color);
            body.add(b);
        }


        size += Snake.checkApples(xCoor,yCoor,max);

       if(xCoor < 0 ) {
           goUp();
           xCoor ++;
       }
       else if(xCoor > max ) {
           goDown();
           xCoor --;
       }
       else if(yCoor < 0 ) {
           goRight();
           yCoor ++;
       }
       else if (yCoor > max) {
           goLeft();
           yCoor --;

            //stop();
       }

       ticks++;

       if (ticks > 25000) {
           if (right) xCoor++;
           if (left) xCoor--;
           if (up) yCoor--;
           if (down) yCoor++;
           ticks = 0;

           b = new BodyPart(xCoor, yCoor, 10, color);
           body.add(b);

           if (body.size() > size) {
               body.remove(0);
           }
       }

    }

    private static synchronized int checkApples(int xCoor, int yCoor, int max) {
        while (apples.size() < 25) {
            int x = r.nextInt(max);
            int y = r.nextInt(max);

            apple = new Apple(x, y, 10);
            apples.add(apple);
        }

        int cnt = 0;
        for (int i = 0; i < apples.size(); i++) {
            if (xCoor == apples.get(i).getxCoor() &&
                    yCoor == apples.get(i).getyCoor()) {
                cnt++;
                apples.remove(i);
                i++;
            }
        }
        return cnt;
    }

    public void move(int direction){
        if(direction == 0 && !left) { // go right
            goRight();
        }
        if(direction == 1 && !right) {
            goLeft();
        }
        if(direction == 2 && !down) {
            goUp();
        }
        if(direction == 3 && !up) {
            goDown();
        }
    }



    private void goDown() {
        left = false;
        right = false;
        down = true;
    }

    private void goUp() {
        left = false;
        right = false;
        up = true;
    }

    private void goLeft() {
        up = false;
        down = false;
        left = true;

    }

    private void goRight() {
        up = false;
        down = false;
        right = true;
    }

    public synchronized  ArrayList<BodyPart> getBody() {
        return body;
    }

    public synchronized static ArrayList<Apple> getApples() {
        return apples;
    }
}