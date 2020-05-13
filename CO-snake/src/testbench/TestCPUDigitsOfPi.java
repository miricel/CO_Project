package testbench;

import java.io.IOException;

import bench.cpu.CPUDigitsOfPi;
import logging.ConsoleLogger;
import logging.FileLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.ITiming;
import timing.Timing;

public class TestCPUDigitsOfPi {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		long time;
		
		CPUDigitsOfPi bench = new CPUDigitsOfPi();
		ITiming timer = new Timing();
		ILogger fileLogger = new FileLogger("writefile.txt");
		ILogger consoleLogger = new ConsoleLogger();
		
		final int workload = 100;
		
		bench.initialize(workload,timer);
		
		timer.start();
		bench.warmUp();
		time = timer.stop();
		TimeUnit WarmupUnit = TimeUnit.Sec;
		fileLogger.writeTime("Warmup took ", time, WarmupUnit  );
		consoleLogger.writeTime("Warmup took ", time, WarmupUnit  );
		
		timer.start();
		for(int i=0; i<12; ++i) {
			timer.resume();
			bench.run(1,5000000);
			bench.run(0,5000000);
			time = timer.pause();
			TimeUnit unit = TimeUnit.Sec;
			fileLogger.write("Run "+  i +":",time);
			consoleLogger.write("Run "+ i +":",time);
			fileLogger.writeTime(" =", time, unit  );
			consoleLogger.writeTime(" =", time, unit  );
		}
		time = timer.stop();
		
		TimeUnit unit = TimeUnit.Sec;
		fileLogger.write("Took ", time, "ns.\n");
		fileLogger.writeTime("Took ", time, unit  );
		consoleLogger.write("Took ", time, "ns.\n");
		consoleLogger.writeTime("Took ", time, unit  );
		fileLogger.close();
	}

}
