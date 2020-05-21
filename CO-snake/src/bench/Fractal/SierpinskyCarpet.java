package bench.Fractal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class SierpinskyCarpet extends JFrame {
    private static Frame f;
    private boolean running=true;
    private int n=6;

    public SierpinskyCarpet(){
        //super("Sierpinsky Carpet");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// <- don't close window
        // when [X] is pressed

        final SierpinskyCarpet gui = this;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(gui,
                        "Are you sure to exit?", "Closing dialog",
                        JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    stop();
                    System.exit(0);
                }
            }
        });


        setBounds(100, 100, 800, 600);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public SierpinskyCarpet(int n){
        super();
        this.n=n;
    }

    private int drawRectangle(Graphics g,  int size, int x, int y){

        System.out.println(size);
        if(!running || size==0 || size==1)
            return 0;
        for(int i=0; i<3&&running; i++)
            for(int j=0; j<3&&running; j++){
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
        g.setColor(Color.pink);
        drawRectangle(g, (int)Math.pow(3, n), 0, 0);
    }

    private void stop(){
        running=false;
    }

    public static void main(String[] args) {
        (f=new SierpinskyCarpet()).setVisible(true);
    }
}