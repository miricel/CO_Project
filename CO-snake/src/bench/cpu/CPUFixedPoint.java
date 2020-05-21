package bench.cpu;

import java.io.IOException;

import bench.IBenchmark;

public class CPUFixedPoint implements IBenchmark{

	private int workload; 
	boolean running = false;
	int i ,j ,k,l;
	int num[];
	int res[] = new int[10];
	int size = 100;
	
	int[] a;
	int[] b;
	int[] c;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		IntegerArithmeticTest();
		//BranchingTest();
		//ArrayAccessTest();
		// (27 + 18) * workload + 20 * size 
	}

	@Override
	public void run(Object... params) throws IOException {
		// TODO Auto-generated method stub
		
		IntegerArithmeticTest();
		//BranchingTest();
		//ArrayAccessTest();
		// (27 + 18) * workload + (20 * size + 2) * workload
	}

	@Override
	public void initialize(Object... params) {
		// TODO Auto-generated method stub
		workload = (int) params[0];
		num = new int[5];
		num[0] = 0;
		num[1] = 1;
		num[2] = 2;
		num[3] = 3;
		num[4] = 4;
		i=3;
		j=1;
		k=1;
		l=2;
		
		a = new int[size];
		b = new int[size];
		c = new int[size];
		
		if( params.length > 1 )
			size = (int) params[1];
		
		running = true;
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
		for( int i=0; i<5; i++ )
			run();
		
	}

	@Override
	public String getResult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void IntegerArithmeticTest() {
		i = 3;
		for(int a = 0; a<size; a++)
		{
			j = num[1] * (k-j ) * (1-k); //6
			k = num[3] * k - (1 - j) * k; //6
			l = (l-k) * (num[1] + j); //5
			res[i-2] = j + k + 1; //4
			res[i-2] = j * k * 1; //4

		}//25 + 2
		
		//27 * size
	}
	
	public void BranchingTest() {
		for(int a = 0; a < workload; a++)
		{
			if (j ==1)
				j = num[2]; 
			else j = num[3];
			if (j > 2)
				j = num[0];
			else j = num[1];
			if(j < 1)
				j = num[1];
			else j = num[0];
		}
	} //5 + 5 + 5 + 3 = 18 => 18 * workload
	
	public void ArrayAccessTest() {
		
		for( k = 0; k<workload; k++ ) 
		{
			for(i=0; i<size; i++)
			{
				a[i] = i; //2
				b[i] = i; //2
				c[i] = size - i ; //3
			}//7 + 2
			// 9*size
			for( i=0; i<size; i++)
			{
				l = c[a[i]]; //3
				c[a[i]] = b[a[i]]; //4
				b[a[i]] = l;  //2
			}//9 + 2
			// 11 * size
			
			//20*size + 2 

		}//(20 * size + 2) * workload
	}
}
