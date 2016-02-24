package com.company;
import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {

    private static Grid grid = new Grid(6, 2);

    private static Vehicle hor_car = new Vehicle(true, 2);
    private static Vehicle hor_truck = new Vehicle(true, 3);
    private static Vehicle ver_car = new Vehicle(false, 2);
    private static Vehicle ver_truck = new Vehicle(false, 3);

    public static void main(String[] args) {
        grid.addVehicle(hor_car, 0, 2);
        grid.addVehicle(ver_truck, 4, 0);

        visualize();

        grid.moveDown(2);
        grid.moveRight(1);

        visualize();

        grid.moveDown(2);
        grid.moveRight(1);

        visualize();

        grid.moveUp(2);
        grid.moveLeft(1);

        visualize();
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
