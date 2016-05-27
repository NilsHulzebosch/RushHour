package com.company;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Type in the number of the puzzle you want to solve: ");
        int puzzleNumber = in.nextInt();
        if (puzzleNumber == 1) {
            solveBreadthFirst(firstPuzzle());
        }

        long start = System.currentTimeMillis();
        //ArrayList<Grid> path = solveBreadthFirst(firstPuzzle());
        //ArrayList<Grid> path = solveBreadthFirst(secondPuzzle());
        //ArrayList<Grid> path = solveBreadthFirst(thirdPuzzle());
        //ArrayList<Grid> path = solveBreadthFirst(fourthPuzzle());
        //ArrayList<Grid> path = solveBreadthFirst(fifthPuzzle());
        //ArrayList<Grid> path = solveBreadthFirst(sixthPuzzle());
        //ArrayList<Grid> path = solveBreadthFirst(seventhPuzzle());
        long end = System.currentTimeMillis();

        //printPath(path);
        //System.out.println("Path length = " + path.get(0).getPathSize());
        System.out.println("Time = " + (end - start));
    }

    private static ArrayList<Grid> solveBreadthFirst(Grid grid) {
        Comparator<Grid> comparator = new PathSizeComparator();
        PriorityQueue<Grid> queue = new PriorityQueue<>(10, comparator);
        HashSet<Grid> hash_set = new HashSet<>();

        // add first grid to queue and hashset
        queue.add(grid);
        hash_set.add(grid);

        int amountOfGrids = 1;
        boolean goalReached = false;
        while(!goalReached) {

            // retrieve and remove first element and generate it's children
            Grid first_grid = queue.poll();

            ArrayList<Grid> new_children = first_grid.generateAllChildren();
            for (Grid child : new_children) {
                amountOfGrids++;

                if (!hash_set.contains(child)) {
                    queue.add(child);
                    hash_set.add(child);
                }

                if (child.goalReached()) {
                    System.out.println("Amount of grids created = " + amountOfGrids);
                    return child.getPath();
                }
            }
        }
        return null;
    }

    private static void printPath(ArrayList<Grid> path) {
        for (int i = path.size() - 1; i >= 0; i--) {
            wipeScreen();
            path.get(i).printGrid();
            //delay(1000);
        }
        System.out.println(path.get(0).getPathSize());
    }

    // adds the first 6x6 board configuration from our assignment
    private static Grid firstPuzzle() {
        Grid grid = new Grid(6);

        grid.addVehicle(true, 2, 3, 2); // red car (gets value 1)
        grid.addVehicle(false, 2, 0, 4);
        grid.addVehicle(true, 2, 1, 4);
        grid.addVehicle(false, 3, 2, 0);
        grid.addVehicle(true, 2, 3, 0);
        grid.addVehicle(false, 3, 5, 0);
        grid.addVehicle(true, 2, 4, 3);
        grid.addVehicle(false, 3, 3, 3);
        grid.addVehicle(true, 2, 4, 5);

        return grid;
    }

    private static Grid secondPuzzle() {
        Grid grid = new Grid(6);

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

        return grid;
    }

    private static Grid thirdPuzzle() {
        Grid grid = new Grid(6);

        // add red hor. car (so it will get number 1)
        grid.addVehicle(true, 2, 0, 2);

        grid.addVehicle(true, 2, 1, 0);     // blue hor. car
        grid.addVehicle(true, 3, 3, 0);     // light-yellow hor. truck

        grid.addVehicle(true, 2, 1, 1);     // orange hor. car
        grid.addVehicle(false, 2, 3, 1);    // blue ver. car
        grid.addVehicle(true, 2, 4, 1);     // turquoise hor. car

        grid.addVehicle(false, 2, 2, 2);    // cyan ver. car
        grid.addVehicle(false, 2, 5, 2);    // cyan ver. car

        grid.addVehicle(true, 2, 0, 3);     // turquoise hor. car
        grid.addVehicle(true, 2, 3, 3);     // blue hor. car

        grid.addVehicle(false, 2, 0, 4);    // orange ver. car
        grid.addVehicle(false, 2, 2, 4);    // turquoise ver. car
        grid.addVehicle(true, 2, 4, 4);     // turquoise hor. car

        return grid;
    }

    private static Grid fourthPuzzle() {
        Grid grid = new Grid(9);

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
        return grid;
    }

    private static Grid fifthPuzzle() {
        Grid grid = new Grid(9);

        // add red hor. car (so it will get number 1)
        grid.addVehicle(true, 2, 6, 4);

        grid.addVehicle(true, 3, 0, 0);     // light-yellow hor. truck
        grid.addVehicle(false, 3, 3, 0);    // purple ver. truck
        grid.addVehicle(false, 2, 5, 0);    // turquoise ver. car
        grid.addVehicle(false, 2, 6, 0);    // blue ver. car

        grid.addVehicle(true, 2, 7, 1);     // turquoise hor. car

        grid.addVehicle(true, 2, 4, 2);     // orange hor. car
        grid.addVehicle(false, 2, 6, 2);    // cyan ver. car

        grid.addVehicle(true, 2, 4, 3);     // blue hor. car
        grid.addVehicle(true, 2, 7, 3);     // orange hor. car

        grid.addVehicle(true, 3, 2, 4);     // dark-yellow hor. truck
        grid.addVehicle(false, 3, 5, 4);    // purple ver. truck
        grid.addVehicle(false, 3, 8, 4);    // light-yellow ver. truck

        grid.addVehicle(false, 2, 0, 5);    // blue ver. car
        grid.addVehicle(false, 2, 2, 5);    // orange ver. car

        grid.addVehicle(true, 2, 3, 6);     // turquoise hor. car
        grid.addVehicle(true, 2, 6, 6);     // turquoise hor. car

        grid.addVehicle(false, 2, 0, 7);    // cyan ver. car
        grid.addVehicle(false, 2, 1, 7);    // turquoise ver. car
        grid.addVehicle(true, 2, 2, 7);     // blue hor. car
        grid.addVehicle(false, 2, 4, 7);    // orange ver. car
        grid.addVehicle(true, 3, 5, 7);     // grey hor. truck
        grid.addVehicle(false, 2, 8, 7);    // cyan ver. car

        grid.addVehicle(true, 2, 2, 8);     // cyan hor. car

        return grid;
    }

    private static Grid sixthPuzzle() {
        Grid grid = new Grid(9);

        // add red hor. car (so it will get number 1)
        grid.addVehicle(true, 2, 0, 4);

        grid.addVehicle(true, 2, 0, 0);     // blue hor. car
        grid.addVehicle(true, 2, 2, 0);     // cyan hor. car
        grid.addVehicle(false, 2, 4, 0);    // turquoise ver. car
        grid.addVehicle(false, 2, 7, 0);    // blue ver. car

        grid.addVehicle(false, 2, 0, 1);    // cyan ver. car
        grid.addVehicle(true, 3, 1, 1);     // dark-yellow hor. truck
        grid.addVehicle(true, 2, 5, 1);     // orange hor. car

        grid.addVehicle(true, 2, 2, 2);     // turquoise hor. car
        grid.addVehicle(false, 2, 4, 2);    // orange ver. car
        grid.addVehicle(false, 2, 5, 2);    // cyan ver. car
        grid.addVehicle(true, 2, 7, 2);     // turquoise hor. car

        grid.addVehicle(false, 2, 2, 3);    // blue ver. car
        grid.addVehicle(false, 3, 3, 3);    // light-yellow ver. truck
        grid.addVehicle(true, 3, 6, 3);     // grey hor. truck

        grid.addVehicle(false, 2, 1, 5);    // orange ver. car
        grid.addVehicle(true, 2, 4, 5);     // cyan hor. car
        grid.addVehicle(true, 2, 6, 5);     // orange hor. car
        grid.addVehicle(false, 3, 8, 5);    // purple ver. truck

        grid.addVehicle(false, 3, 0, 6);    // purple ver. truck
        grid.addVehicle(true, 2, 2, 6);     // turquoise hor. car
        grid.addVehicle(false, 3, 4, 6);    // grey ver. truck
        grid.addVehicle(true, 3, 5, 6);     // yellow hor. truck

        grid.addVehicle(true, 2, 2, 7);     // blue hor. car
        grid.addVehicle(true, 2, 5, 7);     // turquoise hor. car

        grid.addVehicle(true, 3, 1, 8);     // dark-yellow hor. truck

        return grid;
    }

    private static Grid seventhPuzzle() {
        Grid grid = new Grid(12);

        grid.addVehicle(true, 2, 2, 5);     // adds the red car

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

        grid.addVehicle(false, 2, 4, 5);    // orange ver. car
        grid.addVehicle(false, 2, 5, 5);    // cyan ver. car

        grid.addVehicle(true, 3, 0, 6);     // grey hor. truck
        grid.addVehicle(false, 2, 3, 6);    // cyan ver. car
        grid.addVehicle(false, 3, 6, 6);    // light-yellow ver. truck
        grid.addVehicle(false, 2, 7, 6);    // blue ver. car
        grid.addVehicle(false, 2, 9, 6);    // cyan ver. car
        grid.addVehicle(true, 2, 10, 6);    // turquoise hor. car

        grid.addVehicle(true, 3, 0, 7);     // light-yellow hor. truck
        grid.addVehicle(true, 2, 4, 7);     // turquoise hor. car
        grid.addVehicle(true, 2, 10, 7);    // blue hor. car

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

        return grid;
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
