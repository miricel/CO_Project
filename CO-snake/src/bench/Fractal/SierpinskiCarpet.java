package bench.Fractal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class SierpinskiCarpet extends JPanel {
    private static Random rand = new Random();

    public SierpinskiCarpet(){
        super();
        this.setSize(1800, 1000);
        this.setPreferredSize(new Dimension(1000, 1000));
        revalidate();
        repaint();
        this.setVisible(true);
    }

    private int drawRectangle(Graphics2D g,  int size, int x, int y){
        g.setColor(new Color((float)(rand.nextFloat()),(float)(rand.nextFloat()/ 2f + 0.5), (float)(rand.nextFloat()/ 2f + 0.5)));
        if(size==0 || size==1)
            return 0;
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++){
                if(i==1 && j==1){
                    //printsmth
                    int x1 = x+i*size/3;
                    int y2 = y+j*size/3;
                    g.drawRect(x1, y2, size/3, size/3);


                } else {
                    //recursevely call
                    drawRectangle(g, size/3, x+i*size/3, y+j*size/3);
                }
            }
        return 1;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        int n=8;
        drawRectangle(g2d, (int)Math.pow(3, n), 0, 0);
    }
}
