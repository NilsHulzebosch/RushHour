package com.company;
import java.util.*;

public class Main {

    private static final int GRID_SIZE = 9;

    private static Grid grid = new Grid(GRID_SIZE);

    /* Linked List Declaration */
    private static LinkedList<Grid> queue = new LinkedList<>();
    private static HashSet<Grid> hashset = new HashSet<>();

    public static void main(String[] args) {
        //addFirstPuzzle();
        //addSecondPuzzle();
        addFourthPuzzle();
        grid.printGrid();
        System.out.println();

        // add first grid to queue
        queue.add(grid);
        hashset.add(grid);

        boolean isFinished = false;
        while(!isFinished) {

            // retrieve first element and generate all children
            Grid first_grid = queue.getFirst();
            ArrayList<Grid> new_children = first_grid.generateAllChildren();
            for (int i = 0; i < new_children.size(); i++) {
                Grid child = new_children.get(i);
                //child.printGrid();

                if (!hashset.contains(child)) {
                    queue.add(child);
                    hashset.add(child);
                }

                if(child.goalReached()) {
                    isFinished = true;
                    break;
                }
            }

            // remove first element from grid
            queue.remove();
        }

        queue.getLast().printGrid();

        /*
        // print all grids in the queue
        for (int i = 0; i < queue.size(); i++) {
            wipeScreen();
            //System.out.println(queue.size());
            queue.get(i).printGrid();
            delay(1000);
        }
        */
    }

    // adds the first 6x6 board configuration from our assignment
    private static void addFirstPuzzle() {
        grid.addVehicle(true, 2, 3, 2);     // red car (gets value 1)
        grid.addVehicle(false, 2, 0, 4);    // (value 6)
        grid.addVehicle(true, 2, 1, 4);     // (value 2)
        grid.addVehicle(false, 3, 2, 0);    // (value 7)
        grid.addVehicle(true, 2, 3, 0);     // (value 3)
        grid.addVehicle(false, 3, 5, 0);    // (value 8)
        grid.addVehicle(true, 2, 4, 3);     // (value 4)
        grid.addVehicle(false, 3, 3, 3);    // (value 9)
        grid.addVehicle(true, 2, 4, 5);     // (value 5)
    }

    private static void addSecondPuzzle() {
        grid.addVehicle(true, 2, 2, 2);
        grid.addVehicle(true, 2, 2, 0);
        grid.addVehicle(true, 2, 4, 0);
        grid.addVehicle(true, 2, 1, 1);
        grid.addVehicle(true, 2, 3, 1);
        grid.addVehicle(false, 3, 5, 1);
        grid.addVehicle(false, 2, 4, 2);
        grid.addVehicle(true, 2, 0, 3);
        grid.addVehicle(true, 2, 2, 3);
        grid.addVehicle(false, 2, 0, 4);
        grid.addVehicle(false, 2, 3, 4);
        grid.addVehicle(true, 2, 4, 4);
        grid.addVehicle(true, 2, 4, 5);
    }

    private static void addFourthPuzzle() {
        grid.addVehicle(true, 2, 1, 4);
        grid.addVehicle(false, 2, 0, 0);
        grid.addVehicle(true, 3, 1, 0);
        grid.addVehicle(false, 3, 5, 0);
        grid.addVehicle(false, 3, 3, 1);
        grid.addVehicle(true, 3, 6, 1);
        grid.addVehicle(false, 3, 8, 2);
        grid.addVehicle(true, 2, 0, 3);
        grid.addVehicle(true, 3, 5, 3);
        grid.addVehicle(false, 2, 0, 4);
        grid.addVehicle(false, 2, 3, 4);
        grid.addVehicle(false, 3, 2, 5);
        grid.addVehicle(true, 3, 5, 5);
        grid.addVehicle(false, 3, 8, 5);
        grid.addVehicle(true, 2, 0, 6);
        grid.addVehicle(false, 2, 3, 6);
        grid.addVehicle(true, 2, 4, 6);
        grid.addVehicle(false, 2, 0, 7);
        grid.addVehicle(false, 2, 4, 7);
        grid.addVehicle(true, 3, 1, 8);
        grid.addVehicle(true, 2, 5, 8);
        grid.addVehicle(true, 2, 7, 8);
    }

    private static void addSeventhPuzzle() {
        // add red hor. car (so it will get number 1)
        grid.addVehicle(true, 2, 2, 5);

        grid.addVehicle(false, 2, 0, 0);    // turquoise ver. car
        grid.addVehicle(false, 2, 6, 0);    // blue ver. car
        grid.addVehicle(true, 3, 7, 0);     // purple hor. truck
        grid.addVehicle(true, 2, 10, 0);    // cyan hor. car

        grid.addVehicle(false, 2, 5, 1);    // cyan ver. car
        grid.addVehicle(false, 2, 10, 1);   // orange ver. car
        grid.addVehicle(false, 2, 11, 1);   // blue ver. car

        grid.addVehicle(true, 3, 0, 2);     // light-yellow hor. truck
        grid.addVehicle(true, 2, 3, 2);     // orange hor. car
        grid.addVehicle(false, 3, 6, 2);    // dark-yellow ver. truck
        grid.addVehicle(true, 2, 7, 2);     // turquoise hor. car

        grid.addVehicle(false, 3, 0, 3);    // purple ver. truck
        grid.addVehicle(false, 3, 1, 3);    // dark-yellow ver. truck
        grid.addVehicle(false, 2, 5, 3);    // turquoise ver. car
        grid.addVehicle(true, 2, 7, 3);     // orange hor. car
        grid.addVehicle(true, 2, 9, 3);     // cyan hor. car

        grid.addVehicle(true, 3, 2, 4);     // grey hor. truck
        grid.addVehicle(true, 3, 7, 4);     // purple hor. truck

        grid.addVehicle(true, 2, 4, 5);     // orange ver. car
        grid.addVehicle(true, 2, 5, 5);     // cyan ver. car

        grid.addVehicle(true, 3, 0, 6);     // grey hor. truck
        grid.addVehicle(false, 2, 3, 6);    // cyan ver. car
        grid.addVehicle(false, 3, 6, 6);    // light-yellow ver. truck
        grid.addVehicle(false, 2, 7, 6);    // blue ver. car
        grid.addVehicle(false, 2, 9, 6);    // cyan ver. car
        grid.addVehicle(true, 2, 10, 6);    // turquoise hor. car

        grid.addVehicle(true, 3, 0, 7);     // light-yellow hor. truck
        grid.addVehicle(false, 2, 4, 7);    // turquoise hor. car
        grid.addVehicle(false, 2, 10, 7);   // blue hor. car

        grid.addVehicle(true, 2, 0, 8);     // orange hor. car
        grid.addVehicle(false, 2, 2, 8);    // turquoise ver. car
        grid.addVehicle(true, 3, 3, 8);     // purple hor. truck
        grid.addVehicle(true, 3, 7, 8);     // dark-yellow hor. truck
        grid.addVehicle(false, 2, 11, 8);   // turquoise ver. car

        grid.addVehicle(true, 3, 3, 9);     // light-yellow hor. truck
        grid.addVehicle(false, 3, 6, 9);    // grey ver. truck
        grid.addVehicle(true, 2, 8, 9);     // turquoise hor. car
        grid.addVehicle(false, 3, 10, 9);   // purple ver. truck

        grid.addVehicle(false, 2, 9, 10);   // cyan ver. car
        grid.addVehicle(false, 2, 11, 10);  // cyan ver. car

        grid.addVehicle(true, 2, 1, 11);    // turquoise hor. car
        grid.addVehicle(true, 3, 3, 11);    // dark-yellow hor. truck
        grid.addVehicle(true, 2, 7, 11);    // blue hor. car
    }

        // animate the moves
    private static void visualize() {
        wipeScreen();
        grid.printGrid();
        delay(3000);
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
