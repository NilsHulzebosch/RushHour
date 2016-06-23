package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Type in the number of the puzzle you want to solve: ");
        int puzzleNumber = in.nextInt();

        ArrayList<Grid> path;
        long start = System.currentTimeMillis();
        switch (puzzleNumber) {
            case 1: path = solvePuzzle(firstPuzzle()); break;
            case 2: path = solvePuzzle(secondPuzzle()); break;
            case 3: path = solvePuzzle(thirdPuzzle()); break;
            case 4: path = solvePuzzle(fourthPuzzle()); break;
            case 5: path = solvePuzzle(fifthPuzzle()); break;
            case 6: path = solvePuzzle(sixthPuzzle()); break;
            case 7: path = solvePuzzle(seventhPuzzle()); break;
            default: throw new Error("Number must be between 1-7");
        }
        long end = System.currentTimeMillis();
        printPath(path);
        System.out.println("Time = " + (end - start) + "ms");
        System.out.println("Moves = " + path.get(0).getPathSize());

        ArrayList<Move> path_moves = gridsToMoves(path);

        ArrayList<GripperPosition> path_positions = movesToPositions(path_moves);
        writeToFile(path_positions, "positions.txt");

        ArrayList<Joint> path_joints = positionsToJoints(path_positions);
        writeToFile(path_joints, "joints.txt");

        //new Visualization(path);
    }

    // solves puzzle
    private static ArrayList<Grid> solvePuzzle(Grid grid) {
        System.out.println("Puzzle is being solved...");

        Comparator<Grid> comparator = new ScoreComparator();
        PriorityQueue<Grid> queue = new PriorityQueue<>(10, comparator);
        HashSet<Grid> hash_set = new HashSet<>();

        // add first grid to queue and hashset
        queue.add(grid);
        hash_set.add(grid);

        boolean goalReached = false;
        while(!goalReached) {

            // retrieve and remove first element and generate it's children
            Grid first_grid = queue.poll();

            ArrayList<Grid> new_children = first_grid.generateAllChildren();
            for (Grid child : new_children) {

                if (!hash_set.contains(child)) {
                    queue.add(child);
                    hash_set.add(child);
                }

                if (child.goalReached()) {
                    return child.getPath();
                }
            }
        }
        return null;
    }

    private static ArrayList<Move> gridsToMoves(ArrayList<Grid> path) {
        ArrayList<Move> path_moves = new ArrayList<>();
        for (int i = path.size() - 2; i >= 0; i--) {
            Move move = path.get(i).getMove();

            while (i > 0 && path.get(i).getMove().to.x == path.get(i - 1).getMove().from.x &&
                    path.get(i).getMove().to.y == path.get(i - 1).getMove().from.y) {
                i--;
            }

            move.setToPoint(path.get(i).getMove().to);
            path_moves.add(move);
            //System.out.println(move);
        }
        return path_moves;
    }

    private static ArrayList<GripperPosition> movesToPositions(ArrayList<Move> path_moves) {
        ArrayList<GripperPosition> path_positions = new ArrayList<>();
        for (Move move : path_moves) {
            for (GripperPosition gp : move.getPath()) {
                path_positions.add(gp);
            }
        }
        return path_positions;
    }

    private static ArrayList<Joint> positionsToJoints(ArrayList<GripperPosition> path_positions) {
        ArrayList<Joint> path_joints = new ArrayList<>();
        Joint joint;
        for (GripperPosition gp : path_positions) {
            joint = new Joint(gp);
            path_joints.add(joint);
        }
        return path_joints;
    }

    private static void writeToFile(ArrayList<?> array_list, String file_name) {
        try {
            PrintWriter writer = new PrintWriter(file_name, "UTF-8");
            for (Object object : array_list) {
                writer.println(object);
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    // adds the first 6x6 board configuration from the assignment
    private static Grid firstPuzzle() {
        Grid grid = new Grid(6);

        grid.addVehicle(true, 2, 3, 2);     // red car (gets value 1)
        grid.addVehicle(false, 3, 2, 0);    // purple ver. truck
        grid.addVehicle(true, 2, 3, 0);     // cyan hor. car
        grid.addVehicle(false, 3, 5, 0);    // purple ver. truck
        grid.addVehicle(false, 3, 3, 3);    // orange ver. truck
        grid.addVehicle(true, 2, 4, 3);     // orange hor. car
        grid.addVehicle(false, 2, 0, 4);    // orange ver. car
        grid.addVehicle(true, 2, 1, 4);     // cyan hor. car
        grid.addVehicle(true, 2, 4, 5);     // turquoise hor. car

        return grid;
    }

    // adds the second 6x6 board configuration from the assignment
    private static Grid secondPuzzle() {
        Grid grid = new Grid(6);

        grid.addVehicle(true, 2, 2, 2);     // red car (gets value 1)
        grid.addVehicle(true, 2, 2, 0);     // blue hor. car
        grid.addVehicle(true, 2, 4, 0);     // orange hor. car
        grid.addVehicle(true, 2, 1, 1);     // orange hor. car
        grid.addVehicle(true, 2, 3, 1);     // turquoise hor. car
        grid.addVehicle(false, 3, 5, 1);    // yellow
        grid.addVehicle(false, 2, 4, 2);
        grid.addVehicle(true, 2, 0, 3);
        grid.addVehicle(true, 2, 2, 3);
        grid.addVehicle(false, 2, 0, 4);
        grid.addVehicle(false, 2, 3, 4);
        grid.addVehicle(true, 2, 4, 4);
        grid.addVehicle(true, 2, 4, 5);

        return grid;
    }

    // adds the third 6x6 board configuration from the assignment
    private static Grid thirdPuzzle() {
        Grid grid = new Grid(6);

        grid.addVehicle(true, 2, 0, 2);     // red car (gets value 1)
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

    // adds the first 9x9 board configuration from the assignment
    private static Grid fourthPuzzle() {
        Grid grid = new Grid(9);

        grid.addVehicle(true, 2, 1, 4);     // red car (gets value 1)
        grid.addVehicle(false, 2, 0, 0);    // turquoise ver. car
        grid.addVehicle(true, 3, 1, 0);     // yellow hor. truck
        grid.addVehicle(false, 3, 5, 0);    // gray ver. truck
        grid.addVehicle(false, 3, 3, 1);    // purple ver. truck
        grid.addVehicle(true, 3, 6, 1);     // purple hor. truck
        grid.addVehicle(false, 3, 8, 2);    // yellow ver. truck
        grid.addVehicle(true, 2, 0, 3);     // blue hor. car
        grid.addVehicle(true, 3, 5, 3);     // yellow hor. truck
        grid.addVehicle(false, 2, 0, 4);    // blue ver. car
        grid.addVehicle(false, 2, 3, 4);    // turquoise ver. car
        grid.addVehicle(false, 3, 2, 5);    // yellow ver. truck
        grid.addVehicle(true, 3, 5, 5);     // purple hor. truck
        grid.addVehicle(false, 3, 8, 5);    // yellow ver. truck
        grid.addVehicle(true, 2, 0, 6);     // orange hor. car
        grid.addVehicle(false, 2, 3, 6);    // blue ver. car
        grid.addVehicle(true, 2, 4, 6);     // turquoise hor. car
        grid.addVehicle(false, 2, 0, 7);    // blue ver. car
        grid.addVehicle(false, 2, 4, 7);    // orange ver. car
        grid.addVehicle(true, 3, 1, 8);     // gray hor. truck
        grid.addVehicle(true, 2, 5, 8);     // cyan hor. car
        grid.addVehicle(true, 2, 7, 8);     // turquoise hor. car

        return grid;
    }

    // adds the second 9x9 board configuration from the assignment
    private static Grid fifthPuzzle() {
        Grid grid = new Grid(9);

        grid.addVehicle(true, 2, 6, 4);     // red car (gets value 1)
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

    // adds the third 9x9 board configuration from the assignment
    private static Grid sixthPuzzle() {
        Grid grid = new Grid(9);

        grid.addVehicle(true, 2, 0, 4);     // red car (gets value 1)
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

    // adds the 12x12 board configuration from the assignment
    private static Grid seventhPuzzle() {
        Grid grid = new Grid(12);

        grid.addVehicle(true, 2, 2, 5);     // red car (gets value 1)
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

    private static void printPath(ArrayList<Grid> path) {
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println();
            path.get(i).printGrid();
        }
    }
}
