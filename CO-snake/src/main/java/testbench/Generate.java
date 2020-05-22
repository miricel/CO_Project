import java.util.Random;

public class Generate {
	float num[];
    float res[] = new float[10];
    
    public Generate() {
    	num = new float[5];
    	 num[0] = 0;
         num[1] = 1;
         num[2] = 2;
         num[3] = 3;
         num[4] = 4;
    }
	
	  public void FloatingArithmetic() {
	        float i = 3 ,j = 1 ,k = 1 ,l = 2 ;
	        Random r = new Random();
	        float direction = 0;
	        for (int moves = 0; moves < 40; moves ++){
	            for(int a = 0; a<10000; a++)
	            {
	                j = num[1] * (k-j ) * (1-k);
	                k = num[3] * k - (1 - j) * k;
	                l = (l-k) * (num[1] + j);
	                i = i%10;
	                direction = i + j + k + l + r.nextFloat();

	                num[(int) (k%3)] = (j-l) + k * num[1] * j;
	                res[(int) (i-2)] = j + k + 1;
	                res[(int) (i-1)] = j * k * 1;
	            }

	        }
	        direction = direction + 2*r.nextFloat();
	        direction = (int)direction%4;
	        System.out.println(direction);
	    }
	  
	  public static void main(String [] args) {
		  Generate g = new Generate();
		  g.FloatingArithmetic();
	  }

}
