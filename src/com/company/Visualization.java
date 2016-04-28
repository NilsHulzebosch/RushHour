package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Visualization extends JFrame {

    public Visualization(Grid grid) {
        Random rand = new Random();

        setTitle("Rush Hour");
        setLayout(new GridLayout(grid.getSize(), grid.getSize()));
        setSize(800, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        for (int y = 0; y < grid.getSize(); y++) {
            for (int x = 0; x < grid.getSize(); x++) {
                if (grid.getGrid()[x][y] != null) {
                    JLabel label = new JLabel("");

                    Color color;
                    int carnumber = grid.getGrid()[x][y].getNumber();
                    if (carnumber == 1) {
                        color = new Color(255, 0, 0);
                    } else {
                        color = new Color(carnumber*22 % 255, carnumber*10 % 255, carnumber*11 % 255);
                    }
                    label.setBackground(color);
                    label.setOpaque(true);

                    add(label);
                } else {
                    add(new JLabel(""));
                }
            }
        }

        setVisible(true);
    }
}
