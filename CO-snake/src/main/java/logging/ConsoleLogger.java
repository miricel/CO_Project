package logging;

public class ConsoleLogger implements ILogger{

	@Override
	public void write(long x) {
		// TODO Auto-generated method stub
		System.out.print(x);
		
	}
	
	public void write(double x) {
		// TODO Auto-generated method stub
		System.out.print(x);
		
	}

	@Override
	public void write(String s) {
		// TODO Auto-generated method stub
		System.out.print(s);
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
		
		
		System.out.println(convert(value,unit)+" "+unit);
		
	}

	@Override
	public void writeTime(String string, long value, TimeUnit unit) {
		// TODO Auto-generated method stub
		write(string);
		write(convert(value,unit));
		write(" "+unit);
		System.out.println();
		
	}

}
