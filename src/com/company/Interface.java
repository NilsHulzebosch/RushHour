package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Interface extends JFrame implements ActionListener {

    Grid puzzle;
    ArrayList<JButton> buttons = new ArrayList<>();

    public Interface() {
        this.puzzle = puzzle;

        setTitle("Rush Hour - Click the puzzle you want to solve");
        setLayout(new GridLayout(2, 3));
        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        buttons.add(new JButton("Puzzle 1"));
        buttons.add(new JButton("Puzzle 2"));
        buttons.add(new JButton("Puzzle 3"));
        buttons.add(new JButton("Puzzle 4"));
        buttons.add(new JButton("Puzzle 5"));
        buttons.add(new JButton("Puzzle 6"));
        buttons.add(new JButton("Puzzle 7"));

        for (int i = 0; i < 6; i++) {
            add(buttons.get(i));
            buttons.get(i).addActionListener(this);
            revalidate();
            repaint();
        }
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 6; i++) {
            if (e.getSource() == buttons.get(i)) {

            }
        }
    }
}
