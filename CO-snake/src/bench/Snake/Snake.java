package bench.Snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Snake extends Thread {

    private final int max;
    private Screen  screen;
  //  private final ArrayList<Apple> apples;
    private ArrayList<BodyPart> body = new ArrayList<BodyPart>();
    private BodyPart b;
    private Apple apple;
    private int xCoor = 10, yCoor = 10;
    private int size = 15;
    private boolean right = true, left = false, up = false, down =false;
    private Color color;
    private Random r = new Random();

    private int ticks = 0;

    public Snake(Screen screen, Color color,int max,ArrayList<Apple> apples) {
        this.color = color;
        this.screen = screen;
        this.max = max;
       // this.apples = apples;

    }

    @Override
    public void run() {
        while(true){
            tick();
            screen.repaint();
        }
    }

    public void tick() {
        if (body.size() == 0) {
            b = new BodyPart(xCoor, yCoor, 10,color);
            body.add(b);
        }


       /* while(apples.size() < 40) {
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

        */

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

        if(ticks > 50000) {
            if(right) xCoor++;
            if(left) xCoor--;
            if(up) yCoor--;
            if(down) yCoor++;

            ticks = 0;

            b = new BodyPart(xCoor, yCoor, 10,color);
            body.add(b);

            if(body.size() > size) {
                body.remove(0);
            }
        }

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

    public ArrayList<BodyPart> getBody() {
        return body;
    }
}