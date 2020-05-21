package bench.Fractal;

import bench.IBenchmark;

import java.io.IOException;

public class Sierpinsky implements IBenchmark {
    @Override
    public void run() {
        new SierpinskyCarpet().setVisible(true);
    }

    @Override
    public void run(Object... params) throws IOException {
        new SierpinskyCarpet((int)params[1]).setVisible(true);
    }

    @Override
    public void initialize(Object... params) {
    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void warmUp() throws IOException {
        new SierpinskyCarpet(3).setVisible(false);
    }

    @Override
    public String getResult() {
        return null;
    }
}
