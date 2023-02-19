package main;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Counter c = new Counter();
        Lock l = new ReentrantLock();
        Thread t1 = new Thread(new RunManyTimes(1000000, () -> {
            l.lock();
            c.decrement();
            l.unlock();
        }));
        Thread t2 = new Thread(new RunManyTimes(1000000, () -> {
            l.lock();
            c.increment();
            l.unlock();
        }));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
            System.out.println(c.get());
        } catch (Exception e) {
            System.out.println("Oops, something went wrong");
        }

    }
}
