package testbench;

import java.io.IOException;

import bench.Snake.SnakesBenchmark;
import bench.database.SnakeDB;
import timing.TimeUnit;
import timing.ITiming;
import timing.Timing;

public class TestSnakesAlgorithms {

    static boolean running = false;
    static SnakesBenchmark bench = new SnakesBenchmark();

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        long time;

        ITiming timer = new Timing();

        final int workload = 1000000;
        final int size = 40;

        bench.initialize(workload,size);


        running = true;
        timer.start();
        for(int i=0; i<12 && running; ++i) {
            timer.resume();
            bench.run();
            timer.pause();
        }
        time = timer.stop();
        bench.cancel();
        cancel();

        TimeUnit unit = TimeUnit.Micro;
        bench.clean();

        new SnakeDB().doAll(time,bench.getScreen().getFrame());
    }

    public static void cancel(){
        running = false;
        bench.cancel();
    }
}

