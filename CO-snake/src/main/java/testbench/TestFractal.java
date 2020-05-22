package testbench;

import bench.Fractal.SierpinskiBench;
import bench.database.Fractal;
import timing.ITiming;
import timing.Timing;

import java.io.IOException;

public class TestFractal {
    public static void main(String[] args) throws IOException {

        SierpinskiBench bench = new SierpinskiBench();
        ITiming timer = new Timing();

        bench.initialize();
        bench.warmUp();
        timer.start();

        for(int i=0; i<15; i++)
            bench.run();
        long time = timer.stop();

        bench.clean();
        new Fractal().doAll(time);
    }
}
