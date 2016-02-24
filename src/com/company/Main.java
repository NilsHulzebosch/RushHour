package com.company;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JPanel {

    private static final int GRID_SIZE = 6;
    private static final int AMOUNT_OF_VEHICLES = 2;

    private static Grid grid = new Grid(GRID_SIZE, AMOUNT_OF_VEHICLES);

    private static Vehicle hor_car = new Vehicle(true, 2);
    private static Vehicle hor_truck = new Vehicle(true, 3);
    private static Vehicle ver_car = new Vehicle(false, 2);
    private static Vehicle ver_truck = new Vehicle(false, 3);

    public static void main(String[] args) {
        grid.addVehicle(hor_car, 0, 2);
        grid.addVehicle(ver_truck, 4, 0);

        visualize();

        /* Dit was een uitprobeersel, maar het werkt (nog) niet
        int random_move;
        while (!grid.goalReached()) {
            random_move = (int)(Math.random()*4);
            switch (random_move)
            {
                case 0: grid.moveRight(1);
                case 1: grid.moveLeft(1);
                case 2: grid.moveUp(2);
                case 3: grid.moveDown(2);
            }
            visualize();
        }
        */
    }

    private static void visualize() {
        wipeScreen();
        grid.printGrid();
        delay(1000);
    }

    private static void delay(int milliseconds) {
        // delay an amount of milliseconds
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private static void wipeScreen() {
        for(int i = 0; i < 15; i++) {
            System.out.println();
        }
    }
}
