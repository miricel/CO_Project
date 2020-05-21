package bench.Fractal;

import bench.IBenchmark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class SierpinskyBench extends JFrame implements IBenchmark {
    private boolean running=true;
    private int workload=6;
    private Frame fractalFrame;

    public SierpinskyBench(){
        super("Sierpinsky Carpet");
        setBounds(100, 100, 800, 600);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public SierpinskyBench(int workload){
        super();
        this.workload=workload;
    }

    private int drawRectangle(Graphics g,  int size, int x, int y){
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
        drawRectangle(g, (int)Math.pow(3, workload), 0, 0);
    }

    @Override
    public void run() {
        fractalFrame.setVisible(true);
    }

    @Override
    public void run(Object... params) throws IOException {
        new SierpinskyBench((int)params[1]).setVisible(true);
        for(int i=0; i<6; i++)
            new SierpinskyBench((int)params[1]).setVisible(false);
    }

    @Override
    public void initialize(Object... params) {
        fractalFrame=new SierpinskyBench();
    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {
        running = false;
        fractalFrame.dispatchEvent(new WindowEvent(fractalFrame, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void warmUp() throws IOException {
        new SierpinskyBench(3).setVisible(false);
    }

    @Override
    public String getResult() {
        return null;
    }
}
