package main;

import static java.lang.Math.abs;

public class BallThread extends Thread {
    private Ball b;
    private Scoreboard s = null;
    private Thread redThread = null;

    public BallThread(Ball ball){
        b = ball;
    }

    public BallThread(Ball ball, Scoreboard s){
        b = ball;
        this.s = s;
    }

    public BallThread(Ball ball, Scoreboard s, Thread redThread){
        b = ball;
        this.s = s;
        this.redThread = redThread;
    }

    @Override
    public void run(){
        if (redThread != null)
            try {
                redThread.join();
            } catch (Exception e) {

            }
        try{
            for(int i=1; i<10000; i++){
                b.move();
                //System.out.println("Thread name = "
                //        + Thread.currentThread().getName());
                Thread.sleep(5);


                int x = b.getX();
                int y = b.getY();
                int xSize = Ball.getXSIZE();
                int ySize = Ball.getYSIZE();
                int h = 260; //245
                int w = 423; //414
                if ((x < 5 && (y < ySize / 2 || abs(y - (h / 2)) < ySize / 2 || h - y < ySize / 2))||
                   (abs(x - w / 2) < 5 && (y < ySize / 2 || h - y < ySize / 2)) ||
                   (w - x < xSize / 2 && (y < ySize / 2 || abs(y - (h / 2)) < ySize / 2 || h - y < ySize / 2))) {
                    b.delete();
                    if (s != null) {
                        s.updateScore();
                    }
                    return;
                }
            }
        } catch(InterruptedException ex){

        }
    }
}