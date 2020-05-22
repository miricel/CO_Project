package bench.cpu;

import bench.IBenchmark;

public class CPUFixedVsFloatingPoint implements IBenchmark {

	private int result;
	private int size;

	@Override
	public void initialize(Object ... params) {
		this.size = (Integer)params[0];	
	}

	@Override
	public void warmUp() {
		
		for (int i = 0; i < size; ++i) {
			result = i/256; // fixed
			result = (int) (i/256.0); // floating
		}
	}

	@Override
	@Deprecated	
	public void run() {
	}

	@Override
	public void run(Object ...options) {
		result = 0;
		
		switch ((NumberRepresentation) options[0]) {
		case FLOATING:
			for (int i = 0; i < size; ++i)
				result += i/256.0;/**/
			break;
		case FIXED:
			for (int i = 0; i < size; ++i)				
				result += i/256;/**/
			break;
		default:
			break;
		}

	}
	
	@Override
	public void cancel() {
		
	}

	@Override
	public void clean() {
	}

	@Override
	public String getResult() {
		return String.valueOf(result);
	}

}