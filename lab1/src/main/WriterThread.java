package main;

public class WriterThread extends Thread{
    private char c;
    private Thread t = null;

    public WriterThread(char c) {
        this.c = c;
    }

    public void setT(Thread t) {
        this.t = t;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.print(c);
            }
            System.out.println();
            t.interrupt();
            try{
                if (i < 9)
                    Thread.sleep(100000);
            } catch (Exception e) {
                System.out.println("Oops, some error");
            }
        }
    }
}
