package bench.Snake;

import bench.IBenchmark;

import java.awt.*;
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

    @Override
    public void run() {
        // TODO Auto-generated method stub
        IntegerArithmetic();
    }

    @Override
    public void run(Object... params) throws IOException {
        // TODO Auto-generated method stub

        IntegerArithmetic();
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

        Screen screen = new Screen();
        fixedsnake = new Snake(screen);
        floatingsnake = new Snake(screen);

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
        fixedsnake.screen.stop();

    }

    @Override
    public void warmUp() throws IOException {
        // TODO Auto-generated method stub
        for (int i = 0; i < 5; i++)
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
            for(int a = 0; a<workload; a++)
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
            fixedsnake.screen.move1(direction,1);
            floatingsnake.screen.move2(r.nextInt(4),2);
        }
    }



}
