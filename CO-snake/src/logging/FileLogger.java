package logging;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileLogger implements ILogger{
	
private PrintWriter printWriter;
	
	public FileLogger(String filePath) {
        try {
        	
			printWriter = new PrintWriter(filePath);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void write(long x) {
		// TODO Auto-generated method stub
		printWriter.print(x);
		
	}

	@Override
	public void write(String s) {
		// TODO Auto-generated method stub
		printWriter.print(s);
	}

	@Override
	public void write(Object... params) {
		// TODO Auto-generated method stub
		for(Object o: params) 
			if(o instanceof String)
				write((String)o + " ");
			else
				write((long)o);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		printWriter.close();
		
	}
	
	public double convert(long value, TimeUnit unit) {

		double val = 0.0;
		
		switch(unit) {
	      case Nano:
	    	  val = value;
	    	  break;
	      case Micro:
	    	  val = value / 1000.0;
	    	  break;
	      case Milli:
	    	  val = value / 1000000.0;
	    	  break;
	      case Sec:
	    	  val = value / 1000000000.0 ;
	    	  break;
		}
		
		return val;
	}

	@Override
	public void writeTime(long value, TimeUnit unit) {
		// TODO Auto-generated method stub
		printWriter.print(convert(value,unit));
		printWriter.print(" ");
		printWriter.print(unit);
		printWriter.print("\n");
		
	}

	@Override
	public void writeTime(String string, long value, TimeUnit unit) {
		// TODO Auto-generated method stub
		
		printWriter.print(string+" ");
		printWriter.print(convert(value,unit));
		printWriter.print(" ");
		printWriter.print(unit);
		printWriter.print("\n");
		
	}

}
