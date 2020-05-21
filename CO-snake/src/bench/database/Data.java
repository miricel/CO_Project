package bench.database;

import java.lang.management.ManagementFactory;

public class Data {
	
	private String CPU=System.getProperty("os.name"), OS=System.getProperty("os.arch");
	private int cores=Runtime.getRuntime().availableProcessors(), score;
	private long clock;
	private long ram = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
	
	public String CPU()
	{
		return CPU;
	}
	public String OS()
	{
		return OS;
	}
	public int cores()
	{
		return cores;
	}
	public long ram()
	{
		return ram;
	}
	public int score()
	{
		return score;
	}
	public float clock()
	{
		return clock;
	}
	public void setTime(long time)
	{
		clock=time;
	}
	public void setScore(int scor)
	{
		score=scor;
	}
}
