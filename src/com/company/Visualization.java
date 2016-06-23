package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Visualization extends JFrame {

    public Visualization(ArrayList<Grid> path) {
        setTitle("Rush Hour");
        setLayout(new GridLayout(path.get(0).getGridSize(), path.get(0).getGridSize()));
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setVisible(true);

        for (int i = path.size() - 1; i >= 0; i--) {
            showBoard(path.get(i));
            delay(1000);
        }
    }

    private void showBoard(Grid grid) {
        getContentPane().removeAll();
        for (int y = 0; y < grid.getGridSize(); y++) {
            for (int x = 0; x < grid.getGridSize(); x++) {
                if (grid.getGrid()[x][y] != null) {
                    JLabel label = new JLabel("", JLabel.CENTER);

                    Color color;
                    int carnumber = grid.getGrid()[x][y].getNumber();
                    int r;
                    int g;
                    int b;
                    if (grid.getGrid()[x][y].getDirection()) {
                        r = carnumber * 230 % 255;
                        g = carnumber * 201 % 255;
                        b = carnumber * 189 % 255;
                    } else {
                        r = carnumber * 175 % 255;
                        g = carnumber * 201 % 255;
                        b = carnumber * 230 % 255;
                    }

                    if (carnumber == 1) {
                        color = Color.red;
                        label.setText(String.valueOf(grid.getPathSize()));
                        label.setFont(new Font("Courier", Font.BOLD, 32));
                    } else {
                        color = new Color(r, g, b);
                    }
                    label.setBackground(color);
                    label.setOpaque(true);

                    add(label);
                } else {
                    add(new JLabel(""));
                }
            }
        }
        revalidate();
        repaint();
    }

    private static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
