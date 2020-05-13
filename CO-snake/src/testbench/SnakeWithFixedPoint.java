package testbench;

import java.io.IOException;

import bench.Snake.FixedPointSnake;
import bench.cpu.CPUFixedPoint;
import logging.ConsoleLogger;
import logging.FileLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.ITiming;
import timing.Timing;

public class SnakeWithFixedPoint {

    public static void main(String[] args)throws IOException{
        // TODO Auto-generated method stub

        long time;

        FixedPointSnake bench = new FixedPointSnake();
        ITiming timer = new Timing();
        ILogger fileLogger = new FileLogger("writefile.txt");
        ILogger consoleLogger = new ConsoleLogger();

        final int workload = 5000000;
        final int size = 40;

        bench.initialize(workload,size);
        bench.warmUp();

        timer.start();
        for(int i=0; i<12; ++i) {
            timer.resume();
            bench.run();
            time = timer.pause();
            fileLogger.write("Run "+  i +":",time);
            //consoleLogger.write("Run "+ i +":",time);
        }
        time = timer.stop();
        bench.cancel();

        TimeUnit unit = TimeUnit.Micro;
        fileLogger.write("Took ", time, "ns.");
        fileLogger.writeTime("= ", time, unit  );
        consoleLogger.write("Took ", time, "ns.\n");
        consoleLogger.writeTime("Took ", time, unit  );

        double OPS,MOPS;

        double timesec =  (time/1000000000.0);
        OPS = ( (47.0 + 20.0 * size  ) * workload )/time ;
        MOPS = OPS /  1e6 ;


        fileLogger.write("OPS ", timesec+"\n");
        consoleLogger.write("OPS ", OPS+"\n");
        fileLogger.write("MOPS ", MOPS+"\n");
        consoleLogger.write("MOPS ", MOPS+"\n");

        bench.clean();
        fileLogger.close();
    }
}

