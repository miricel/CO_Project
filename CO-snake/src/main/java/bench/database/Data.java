package bench.database;

import java.lang.management.ManagementFactory;
import com.sun.jna.platform.win32.Advapi32Util;
import static com.sun.jna.platform.win32.WinReg.HKEY_LOCAL_MACHINE;

public class Data {
	
	private String CPU=Advapi32Util.registryGetStringValue
			(HKEY_LOCAL_MACHINE,
					"HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\0\\",
					"ProcessorNameString"), OS=System.getProperty("os.name");
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
