package testbench;

import logging.ConsoleLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.ITiming;
import timing.Timing;

import java.io.IOException;

import bench.IBenchmark;
//import bench.cpu.CPUFixedPoint;
import bench.cpu.CPUFixedVsFloatingPoint;
import bench.cpu.NumberRepresentation;
import bench.cpu.NumberRepresentation;

public class TestCPUFixedVsFloatingPoint {

	public static void main(String[] args) throws IOException {
		ITiming timer = new Timing();
		ILogger log = /* new FileLogger("bench.log"); */new ConsoleLogger();
		TimeUnit timeUnit = TimeUnit.Milli;

		IBenchmark bench = new CPUFixedVsFloatingPoint();
		bench.initialize(10000000);
		bench.warmUp();

		timer.start();
		bench.run(NumberRepresentation.FIXED);
		long time = timer.stop();
		log.writeTime("Finished in", time, timeUnit);
		log.write("Result is", bench.getResult());
		log.write("\n");
		
		timer.start();
		bench.run(NumberRepresentation.FIXED);
		time = timer.stop();
		log.writeTime("Finished in", time, timeUnit);
		log.write("Result is", bench.getResult());

		bench.clean();
		log.close();
	}
}
