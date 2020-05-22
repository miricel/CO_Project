package timing;

public class Timing implements ITiming{

	private long elapsedTime, totalTime;
	private boolean paused;

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
		totalTime = 0;
		elapsedTime = System.nanoTime();
		paused = false;
		
	}

	@Override
	public long stop() {
		// TODO Auto-generated method stub
		
		long temp = System.nanoTime();
		if(paused)
			return totalTime;
		 totalTime+= temp - elapsedTime;
		
		return totalTime;
		
	}

	@Override
	public long pause() {
		// TODO Auto-generated method stub
		long temp = System.nanoTime() - elapsedTime;
		totalTime += temp;
		paused = true;
		return temp;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
		elapsedTime = System.nanoTime();
		paused = false;
	}
	
	public String toString() {
		return " elapseTime ";
	}

}
