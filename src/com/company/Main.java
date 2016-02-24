package com.company;

public class Main {

    private static final int GRID_SIZE = 6;
    private static final int AMOUNT_OF_VEHICLES = 9;

    private static Grid grid = new Grid(GRID_SIZE, AMOUNT_OF_VEHICLES);

    public static void main(String[] args) {

        // adds the first 6x6 board configuration from our assignment
        grid.addVehicle(true, 2, 3, 2);     // red car (gets value 1)
        grid.addVehicle(true, 2, 1, 4);     // (value 2)
        grid.addVehicle(true, 2, 3, 0);     // (value 3)
        grid.addVehicle(true, 2, 4, 3);     // (value 4)
        grid.addVehicle(true, 2, 4, 5);     // (value 5)
        grid.addVehicle(false, 2, 0, 4);    // (value 6)
        grid.addVehicle(false, 3, 2, 0);    // (value 7)
        grid.addVehicle(false, 3, 5, 0);    // (value 8)
        grid.addVehicle(false, 3, 3, 3);    // (value 9)

        visualize();
        grid.moveDown(7);
        visualize();
        grid.moveUp(6);
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
