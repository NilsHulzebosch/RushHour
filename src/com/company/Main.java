package com.company;
import java.util.ArrayList;

public class Main {

    private static final int GRID_SIZE = 6;
    private static final int AMOUNT_OF_VEHICLES = 9;

    private static Grid grid = new Grid(GRID_SIZE, AMOUNT_OF_VEHICLES);


    public static void main(String[] args) {

        // adds the first 6x6 board configuration from our assignment
        grid.addVehicle(true, 2, 3, 2);     // red car (gets value 1)
        grid.addVehicle(false, 2, 0, 4);    // (value 6)
        grid.addVehicle(true, 2, 1, 4);     // (value 2)
        grid.addVehicle(false, 3, 2, 0);    // (value 7)
        grid.addVehicle(true, 2, 3, 0);     // (value 3)
        grid.addVehicle(false, 3, 5, 0);    // (value 8)
        grid.addVehicle(true, 2, 4, 3);     // (value 4)
        grid.addVehicle(false, 3, 3, 3);    // (value 9)
        grid.addVehicle(true, 2, 4, 5);     // (value 5)

        visualize();

        /*
        // temporary implementation of "queue"
        ArrayList<Grid> queue = new ArrayList<>();
        int counter = 1;
        queue.add(grid);

        // adds all possible following grids to "queue"
        for (int i = 1; i <= AMOUNT_OF_VEHICLES; i++) {
            if (grid.moveRightIsPossible(i) && grid.moveLeftIsPossible(i)) {
                Grid new_grid = new Grid(grid);
                new_grid.moveRight(i);
                queue.add(new_grid);

                Grid new_grid2 = new Grid(grid);
                new_grid2.moveLeft(i);
                queue.add(new_grid2);

                counter = counter + 2;
            } else if (grid.moveRightIsPossible(i)) {
                Grid new_grid = new Grid(grid);
                new_grid.moveRight(i);
                queue.add(new_grid);
                counter++;
            } else if (grid.moveLeftIsPossible(i)) {
                Grid new_grid = new Grid(grid);
                new_grid.moveLeft(i);
                queue.add(new_grid);
                counter++;
            } else if (grid.moveDownIsPossible(i) && grid.moveUpIsPossible(i)) {
                Grid new_grid = new Grid(grid);
                new_grid.moveDown(i);
                queue.add(new_grid);

                Grid new_grid2 = new Grid(grid);
                new_grid2.moveUp(i);
                queue.add(new_grid2);
                counter = counter + 2;
            } else if (grid.moveDownIsPossible(i)) {
                Grid new_grid = new Grid(grid);
                new_grid.moveDown(i);
                queue.add(new_grid);
                counter++;
            } else if (grid.moveUpIsPossible(i)) {
                Grid new_grid = new Grid(grid);
                new_grid.moveUp(i);
                queue.add(new_grid);
                counter++;
            }
        }

        for (int i = 0; i < counter; i++) {
            wipeScreen();
            queue.get(i).printGrid();
            delay(3000);
        }
        */

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
        for(int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}
