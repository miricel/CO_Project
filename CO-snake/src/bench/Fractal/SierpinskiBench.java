package bench.Fractal;

import bench.IBenchmark;

import javax.swing.*;
import java.io.IOException;

public class SierpinskiBench implements IBenchmark {
    @Override
    public void run() {
        JFrame frame=new JFrame();
        frame.setSize(1800, 1000);
        frame.setTitle("Sierpinski Carpet");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(new SierpinskiCarpet());
        frame.setVisible(true);

        frame.repaint();
        System.out.println("Done");
    }

    @Override
    public void run(Object... params) throws IOException {
        run();
    }

    @Override
    public void initialize(Object... params) {
    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void warmUp() throws IOException {
        new SierpinskiCarpet();
    }

    @Override
    public String getResult() {
        return null;
    }
}