package bench.cpu;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import bench.IBenchmark;
import logging.TimeUnit;

public class CPUDigitsOfPi implements IBenchmark{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(Object... params) throws IOException {
		// TODO Auto-generated method stub
		
		int option = (Integer) params[0];
		
		switch (option) {
		case 0:
			ComputePiSeries((int)params[1]);
			break;
		case 1:
			PiDigitsBigDecimal( (int) params[1] );
			break;
		default:  
			throw new IllegalArgumentException("Option is invalid, must be between 0-1");
		}
		
	}
	
	/* pi = 4/1 - 4/3 + 4/5 - 4/7 + 4/9 - ... */
	public void ComputePiSeries( int n) {
		
		int odd = 1;
		double pi = 0.0;
		double current = 0.0;
		
		for( int i=0; i<n; i++)
		{
			current = 4.0/odd;
			if( i%2 == 0 )
				pi += current;
			else pi -= current;
			
			odd += 2;
		}
		
		System.out.println(pi);
		
	}
	
	public void PiDigitsBigDecimal(int n){
		
		BigDecimal odd = new BigDecimal("1.0");
		BigDecimal pi = new BigDecimal("0.0") ;
		BigDecimal current = new BigDecimal("0.0");
		BigDecimal two = new BigDecimal("2.0");
		MathContext mc = new MathContext(20, RoundingMode.FLOOR);
		
		
		for( int i=0; i<n; i++)
		{
			current = new BigDecimal( "4.0" );
			current = current.divide(odd, mc);
			//System.out.println(current+"---"+odd);
			if( i%2 == 0 )
				pi = pi.add(current);
			else pi = pi.subtract(current);
			
			odd = odd.add( two );
		}
		
		System.out.println(pi);
	}

	@Override
	public void initialize(Object... params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warmUp() throws IOException {
		// TODO Auto-generated method stub
		
		for(int i=0; i<5; ++i) {
			run(1,5000000);
			run(0,5000000);
		}
		
	}

	@Override
	public String getResult() {
		// TODO Auto-generated method stub
		return null;
	}

}
