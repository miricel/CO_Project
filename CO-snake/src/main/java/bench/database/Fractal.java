package bench.database;

import testbench.gui.finishFrame;

import java.io.IOException;

public class Fractal {
	public void doAll(long time)
	{
		DatabaseFractal database = DatabaseFractal.getInstance();
		database.readFromFile();
		
		Data newData = new Data();
		long bestTime=200000000L;
		newData.setTime(time);
		newData.setScore((int)(100*newData.clock()/bestTime));
		
		database.getData().add(newData);	
		database.writeToFile();

		try {
			finishFrame f = new finishFrame(newData.score(), time);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
