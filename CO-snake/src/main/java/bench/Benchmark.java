package bench;

import java.io.*;
import java.util.ArrayList;
import java.util.Timer;

import logging.TimeUnit;
import timing.ITiming;
import timing.Timing;

public class Benchmark implements IBenchmark{

	private ArrayList list = new ArrayList(); 
	boolean running = false;
	private ITiming timer;
	
	@Override
	public void run(Object... params) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("file.txt")));
		String newline;
		
		
			while ( (newline = in.readLine()) != null   && running) 
			{
				 String[] numbers = newline.split(" ");
				 for(int i=0; i<numbers.length && running; i++)
		         {
					 list.add(numbers[i]);
		         }
			}
			
			
			
		in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("IO Error "+e.getMessage());
		}
		
		running = true;
				
		for (int i=0; i<list.size() && running; i++)
			if(list.get(i).equals("15"))
				System.out.println("15 is the array!");
				
	

		System.out.println(list );
	}

	@Override
	public void initialize(Object... params) {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList(); 
		timer = (Timing)params[1];
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		list.clear();
		timer = null;
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		running = false;
	}

	@Override
	public void warmUp() throws IOException {
		// TODO Auto-generated method stub
		
		for(int i=0; i<5; ++i) 
			run();
	}

	@Override
	public String getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
