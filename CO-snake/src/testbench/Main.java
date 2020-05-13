package testbench;

import java.io.IOException;

import bench.Benchmark;
import logging.ConsoleLogger;
import logging.FileLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.ITiming;
import timing.Timing;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		long time;
		
		Benchmark bench = new Benchmark();
		ITiming timer = new Timing();
		ILogger fileLogger = new FileLogger("writefile.txt");
		ILogger consoleLogger = new ConsoleLogger();
		
		final int workload = 100;
		
		bench.initialize(workload,timer);
		bench.warmUp();
		timer.start();
		for(int i=0; i<12; ++i) {
			timer.resume();
			bench.run();
			time = timer.pause();
			fileLogger.write("Run "+  i +":",time);
			consoleLogger.write("Run "+ i +":",time);
		}
		time = timer.stop();
		
		TimeUnit unit = TimeUnit.Milli;
		fileLogger.write("Took ", time, "ns.\n");
		fileLogger.writeTime("Took ", time, unit  );
		consoleLogger.write("Took ", time, "ns.\n");
		consoleLogger.writeTime("Took ", time, unit  );
		fileLogger.close();
	}

}
