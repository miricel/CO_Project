package testbench;

import bench.Fractal.SierpinskiBench;
import bench.IBenchmark;
import logging.ConsoleLogger;
import logging.FileLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.ITiming;
import timing.Timing;

import java.io.IOException;

public class TestFractal {
    public static void main(String[] args) throws IOException {


        IBenchmark bench = new SierpinskiBench();
        ITiming timer = new Timing();
        ILogger fileLogger = new FileLogger("writefile.txt");
        ILogger consoleLogger = new ConsoleLogger();

        bench.initialize();
        bench.warmUp();
        //bench.cancel();
        timer.start();

        //for(int i=0; i<5; i++)
        bench.run();
        long time = timer.stop();

        TimeUnit unit = TimeUnit.Micro;
        consoleLogger.write("Took ", time, "ns.\n");
        consoleLogger.writeTime("Took ", time, unit  );

        /*double OPS,MOPS;

        double timesec =  (time/1000000000.0);
        OPS = ( (47.0 + 20.0 * size  ) * workload )/time ;
        MOPS = OPS /  1e6 ;


        fileLogger.write("OPS ", timesec+"\n");
        consoleLogger.write("OPS ", OPS+"\n");
        fileLogger.write("MOPS ", MOPS+"\n");
        consoleLogger.write("MOPS ", MOPS+"\n");
        */

        bench.clean();
        fileLogger.close();
    }
}
