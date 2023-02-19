package main;

import javax.swing.*;

public class Scoreboard {
    private int score = 0;
    private JLabel label;

    public Scoreboard(JLabel l) {
        this.label = l;
    }

    public void updateScore() {
        label.setText("Score: " + ++score);
    }
}
