package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    private int score = 0;
    private Thread lastRedThread = null;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);


        JButton buttonStart = new JButton("Start");
        JButton buttonRed = new JButton("Add red" );
        JButton buttonBlue = new JButton("Add blue" );
        JLabel labelScore = new JLabel("Score: 0");
        Scoreboard s = new Scoreboard(labelScore);
        JButton buttonStop = new JButton("Stop");
        buttonStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas);
                canvas.add(b);

                BallThread thread = new BallThread(b, s);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());
            }
        });
        buttonRed.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas);
                b.setColor("red");
                canvas.add(b);

                BallThread thread = new BallThread(b, s);
                thread.setPriority(Thread.MAX_PRIORITY);
                thread.start();
                lastRedThread = thread;
                System.out.println("Thread name = " +
                        thread.getName());
            }
        });
        buttonBlue.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas);
                b.setColor("blue");
                canvas.add(b);

                BallThread thread = new BallThread(b, s, lastRedThread);
                thread.setPriority(Thread.MIN_PRIORITY);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonRed);
        buttonPanel.add(buttonBlue);
        buttonPanel.add(buttonStop);
        buttonPanel.add(labelScore);
        content.add(buttonPanel, BorderLayout.SOUTH);

    }

}