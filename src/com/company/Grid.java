package com.company;

/**
 * Created by Sjors on 11-2-2016.
 */
public class Grid {

    private int size;
    private int[][] grid;
    private int car_number = 1;

    public Grid(int size) {
        this.size = size;
        this.grid = new int[size][size];
    }

    public void addVehicle(Vehicle vehicle, int x, int y) {
        for (int i = 0; i < vehicle.getLength(); i++) {
            if (vehicle.getDirection()) {
                grid[x + i][y] = car_number;
            } else {
                grid[x][y + i] = car_number;
            }
        }
        car_number++;
    }

    public void moveRight(Vehicle vehicle, int x, int y) {
        // check whether x+vehicle.getLength() passes the grid size (to avoid ArrayIndexOutOfBoundsException)
        // also make sure it's the first car_number of the car and not the middle/last one
        if(x+vehicle.getLength() < 6 && (x == 0 || grid[x-1][y] != grid[x][y])) {

            // check whether that index is a 'free' spot (i.e. = 0)
            if(grid[x + vehicle.getLength()][y] == 0) {
                grid[x + vehicle.getLength()][y] = grid[x][y];
                grid[x][y] = 0;
            }
        }
    }

    public void moveLeft(Vehicle vehicle, int x, int y) {
        grid[x + vehicle.getLength() - 1][y] = 0;
        grid[x - 1][y] = grid[x][y];
    }

    public void moveDown(Vehicle vehicle, int x, int y) {
        // check whether y+vehicle.getLength() passes the grid size (to avoid ArrayIndexOutOfBoundsException)
        // also make sure it's the first car_number of the car and not the middle/last one
        if(y+vehicle.getLength() < 6 && (y == 0 || grid[x][y-1] != grid[x][y])) {

            // check whether that index is a 'free' spot (i.e. = 0)
            if(grid[x][y + vehicle.getLength()] == 0) {
                grid[x][y + vehicle.getLength()] = grid[x][y];
                grid[x][y] = 0;
            }
        }
    }

    public void moveUp(Vehicle vehicle, int x, int y) {
        grid[x][y + vehicle.getLength() - 1] = 0;
        grid[x][y - 1] = grid[x][y];
    }

    public void printGrid() {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(grid[j][i] + " ");
            }
            System.out.println();
        }
    }
}
