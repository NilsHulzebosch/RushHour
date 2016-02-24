package com.company;

public class Main {

    private static final int GRID_SIZE = 6;
    private static final int AMOUNT_OF_VEHICLES = 9;

    private static Grid grid = new Grid(GRID_SIZE, AMOUNT_OF_VEHICLES);

    private static Vehicle hor_car = new Vehicle(true, 2);
    private static Vehicle hor_truck = new Vehicle(true, 3);
    private static Vehicle ver_car = new Vehicle(false, 2);
    private static Vehicle ver_truck = new Vehicle(false, 3);

    public static void main(String[] args) {

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

        visualize();
        grid.moveUp(6);
        visualize();
        grid.moveDown(7);
        visualize();
    }

    // animate the moves
    private static void visualize() {
        wipeScreen();
        grid.printGrid();
        delay(1000);
    }

    // delay an amount of milliseconds
    private static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    // wipe the screen
    private static void wipeScreen() {
        for(int i = 0; i < 15; i++) {
            System.out.println();
        }
    }
}
