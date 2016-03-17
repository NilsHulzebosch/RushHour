package com.company;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        solveBreadthFirst(firstPuzzle());
        long end = System.currentTimeMillis();
        //solveBreadthFirst(secondPuzzle());

        System.out.println("Time = " + (end - start));
    }

    private static ArrayList<Grid> solveBreadthFirst(Grid grid) {
        LinkedList<Grid> queue = new LinkedList<>();
        HashSet<Grid> hash_set = new HashSet<>();

        // add first grid to queue
        queue.add(grid);
        hash_set.add(grid);

        boolean goalReached = false;
        while(!goalReached) {

            // retrieve first element and generate all children
            Grid first_grid = queue.getFirst();
            ArrayList<Grid> new_children = first_grid.generateAllChildren();
            for (int i = 0; i < new_children.size(); i++) {
                Grid child = new_children.get(i);

                if (!hash_set.contains(child)) {
                    queue.add(child);
                    hash_set.add(child);
                }

                if(child.goalReached()) {
                    goalReached = true;
                    break;
                }
            }

            // remove first element from grid
            queue.remove();
        }
        // queue.getLast().printGrid();
        return getPath(queue.getLast());
    }

    private static ArrayList<Grid> getPath(Grid child) {
        ArrayList<Grid> path = new ArrayList<>();
        path.add(child);

        while (child.getParent() != null) {
            Grid parent = child.getParent();
            path.add(parent);
            child = parent;
        }

        return path;
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
