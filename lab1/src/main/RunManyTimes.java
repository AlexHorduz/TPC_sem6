package main;

public class RunManyTimes implements Runnable{
    private int n;
    private Runnable r;
    public RunManyTimes(int n, Runnable r) {
        this.n = n;
        this.r = r;
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            r.run();
        }
    }
}
