package testbench;

import java.io.IOException;

import bench.Snake.FixedPointSnake;
import bench.database.SnakeDB;
import logging.ConsoleLogger;
import logging.FileLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.ITiming;
import timing.Timing;

public class SnakeWithFixedPoint {

    static boolean running = false;
    static FixedPointSnake bench = new FixedPointSnake();

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        long time;

        ITiming timer = new Timing();
        ILogger fileLogger = new FileLogger("writefile.txt");
        ILogger consoleLogger = new ConsoleLogger();

        final int workload = 1000000;
        final int size = 40;

        bench.initialize(workload,size);


        running = true;
        timer.start();
        for(int i=0; i<12 && running; ++i) {
            timer.resume();
            bench.run();
            time = timer.pause();
            fileLogger.write("Run "+  i +":",time);
            consoleLogger.write("Run "+ i +":",time);
        }
        time = timer.stop();
        bench.cancel();

        TimeUnit unit = TimeUnit.Micro;
        fileLogger.write("Took ", time, "ns.");
        fileLogger.writeTime("= ", time, unit  );
        consoleLogger.write("Took ", time, "ns.\n");
        consoleLogger.writeTime("Took ", time, unit  );

        bench.clean();
        fileLogger.close();

        new SnakeDB().doAll(time);


    }

    public static void cancel(){
        running = false;
        bench.cancel();
    }
}

