package testbench;

import java.io.IOException;

import bench.cpu.CPUFixedPoint;
import logging.ConsoleLogger;
import logging.FileLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.ITiming;
import timing.Timing;

public class TestCPUFixedPoint {

	public static void main(String[] args)throws IOException{
		// TODO Auto-generated method stub
	
		long time;
	
		CPUFixedPoint bench = new CPUFixedPoint();
		ITiming timer = new Timing();
		ILogger fileLogger = new FileLogger("writefile.txt");
		ILogger consoleLogger = new ConsoleLogger();
	
		final int workload = 5000000;
		final int size = 40;
	
		bench.initialize(workload,size);

		timer.start();
		bench.warmUp();
		time = timer.stop();
		TimeUnit WarmupUnit = TimeUnit.Sec;
		fileLogger.writeTime("Warmup took ", time, WarmupUnit  );
		consoleLogger.writeTime("Warmup took ", time, WarmupUnit  );

		timer.start();
		bench.run();
		time = timer.stop();

		TimeUnit unit = TimeUnit.Micro;
		fileLogger.write("Took ", time, "ns.");
		fileLogger.writeTime("= ", time, unit  );
		consoleLogger.write("Took ", time, "ns.\n");
		consoleLogger.writeTime("Took ", time, unit  );
		
		double OPS,MOPS;
		
		double timesec =  (time/1000000000.0);
		OPS = ( (27.0* size  ) /time) ;
		MOPS = OPS /  1e6 ;
		

		fileLogger.write("OPS ", timesec+"\n");
		consoleLogger.write("OPS ", OPS+"\n");
		fileLogger.write("MOPS ", MOPS+"\n");
		consoleLogger.write("MOPS ", MOPS+"\n");
		
		bench.clean();
		fileLogger.close();
	}
}

