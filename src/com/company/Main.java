package com.company;
import javax.swing.*;

public class Main extends JPanel {

    private static Grid grid = new Grid(6, 2);

    private static Vehicle hor_car = new Vehicle(true, 2);
    private static Vehicle hor_truck = new Vehicle(true, 3);
    private static Vehicle ver_car = new Vehicle(false, 2);
    private static Vehicle ver_truck = new Vehicle(false, 3);

    public static void main(String[] args) {
        // adds grid from Grid class
        //Grid grid = new Grid(6);

        // adds vehicle types from Vehicle class
        //Vehicle hor_car = new Vehicle(true, 2);
        //Vehicle hor_truck = new Vehicle(true, 3);
        //Vehicle ver_car = new Vehicle(false, 2);
        //Vehicle ver_truck = new Vehicle(false, 3);

        // adds the first 6x6 board configuration from our assignment
        grid.addVehicle(hor_car, 3, 2);     // red car (gets value 1)
        grid.addVehicle(hor_car, 1, 4);     // (value 2)
        grid.addVehicle(hor_car, 3, 0);     // (value 3)
        grid.addVehicle(hor_car, 4, 3);     // (value 4)
        grid.addVehicle(hor_car, 4, 5);     // (value 5)
        grid.addVehicle(ver_car, 0, 4);     // (value 6)
        grid.addVehicle(ver_truck, 2, 0);   // (value 7)
        grid.addVehicle(ver_truck, 5, 0);   // (value 8)
        grid.addVehicle(ver_truck, 3, 3);   // (value 9)

        wipeScreen();
        grid.printGrid();

        /*
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
