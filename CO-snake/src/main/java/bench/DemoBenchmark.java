package bench;

import java.io.IOException;

import timing.Timing;

public class DemoBenchmark implements IBenchmark{

	private int n;
	private double offset;
	private Timing timer;
	private boolean running;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(n);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		this.offset = (timer.stop() - n * 1e+6) /(n * 1e+6); 
	}

	@Override
	public void run(Object... params) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(Object... params) {
		// TODO Auto-generated method stub
		
		running = true;
		n = (int)params[0];
		
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		running = false;
		
		
	}

	@Override
	public void warmUp() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getResult() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
