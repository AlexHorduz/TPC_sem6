package main;

public class MyThread extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted? " + ((Thread.interrupted())?"yes": "no"));
        }
    }
}
