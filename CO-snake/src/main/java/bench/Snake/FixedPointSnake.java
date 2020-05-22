package bench.Snake;

import bench.IBenchmark;
import testbench.SnakeWithFixedPoint;

import java.io.IOException;
import java.util.Random;

public class FixedPointSnake implements IBenchmark {

    private int workload;
    boolean running = false;
    int i ,j ,k,l;
    int num[];
    int res[] = new int[10];
    int size = 100;
    private Snake fixedsnake;
    private Snake floatingsnake;
    private Screen screen;
    private static Thread fixed;
    private static Thread floating;
    private float[] numf;
    private float[] resf = new float[10];

    @Override
    public void run() {
        // TODO Auto-generated method stub
        fixed =  new Thread(){
            @Override
            public void run() {
                IntegerArithmetic();
            }
        };

        floating =  new Thread(){
            @Override
            public void run() {
                FloatingArithmetic();
            }
        };

        fixed.start();
        floating.start();

        try {
            fixed.join();
            floating.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run(Object... params) throws IOException {
        // TODO Auto-generated method stub


    }

    @Override
    public void initialize(Object... params) {
        // TODO Auto-generated method stub
        workload = (int) params[0];
        num = new int[5];
        numf = new float[5];
        num[0] = 0;
        num[1] = 1;
        num[2] = 2;
        num[3] = 3;
        num[4] = 4;
        numf[0] = (float) 0.0;
        numf[1] = (float) 1.0;
        numf[2] = (float) 2.0;
        numf[3] = (float) 3.0;
        numf[4] = (float) 4.0;
        i=3;
        j=1;
        k=1;
        l=2;

        screen = new Screen();
        fixedsnake = screen.getFixedsnake();
        floatingsnake = screen.getFloatingsnake();

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

        fixed.stop();
        floating.stop();
        screen.stop();

        try {
            fixed.join();
            floating.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void warmUp() throws IOException {
        // TODO Auto-generated method stub
        for (int i = 0; i < 3; i++)
            run();

    }

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    public void IntegerArithmetic() {
        i = 3;
        Random r = new Random();
        int direction = 0;
        for (int moves = 0; moves < 40; moves ++){
            for(int a = 0; a<workload && running; a++)
            {
                j = num[1] * (k-j ) * (1-k);
                k = num[3] * k - (1 - j) * k;
                l = (l-k) * (num[1] + j);
                i = i%10;
                direction = i + j + k + l + r.nextInt(284);

                num[k%3] = (j-l) + k * num[1] * j;
                res[i-2] = j + k + 1;
                res[i-1] = j * k * 1;
            }

            direction %= 4;
            fixedsnake.move(direction);
            screen.addPurple();
        }
    }

    public void FloatingArithmetic() {
        float i = (float) 3.0, j = (float) 1.0, k = (float) 1.0, l = (float) 2.0;
        Random r = new Random();
        float direction = 0;
        for (int moves = 0; moves < 40; moves++) {
            for (int a = 0; a < workload && running; a++) {
                j = numf[1] * (k - j) * (1 - k);
                k = numf[3] * k - (1 - j) * k;
                l = (l - k) * (numf[1] + j);
                i = i % 10;
                direction = i + j + k + l + r.nextFloat();

                numf[(int) (k % 3)] = ((j - l) + k * numf[1] * j);
                resf[(int) (i - 2)] = (j + k + 1);
                resf[(int) (i - 1)] = j * k * 1;


            }
            direction = direction + 2 * r.nextFloat();
            direction = (int) direction % 4;
            floatingsnake.move((int) direction);
            screen.addGreen();

        }
    }

}
