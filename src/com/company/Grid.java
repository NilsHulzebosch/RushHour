package com.company;

public class Grid {

    private int size;
    private int amount_of_vehicles;
    private Vehicle[][] grid;

    private int vehicle_number = 1;


    // constructor
    public Grid(int size, int amount_of_vehicles) {
        this.size = size;
        this.grid = new Vehicle[size][size];
        this.amount_of_vehicles = amount_of_vehicles;
    }

    public Grid(Grid previous) {
        this.size = previous.size;
        this.amount_of_vehicles = previous.amount_of_vehicles;

        this.grid = new Vehicle[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[j][i] = previous.grid[j][i];
            }
        }
    }

    // adds vehicle to grid and array of all vehicles
    public void addVehicle(boolean direction, int length, int x, int y) {
        Vehicle vehicle = new Vehicle(direction, length, vehicle_number);

        for (int i = 0; i < length; i++) {
            if (direction) {
                grid[x + i][y] = vehicle;
            } else {
                grid[x][y + i] = vehicle;
            }
        }
        vehicle_number++;
    }

    // moves vehicle 1 position right
    public void moveRight(int x, int y) {
        int length = grid[x][y].getLength();

        grid[x + length][y] = grid[x][y];
        grid[x][y] = null;
    }

    // moves vehicle 1 position left
    public void moveLeft(int x, int y) {
        int length = grid[x][y].getLength();

        grid[x + length - 1][y] = null;
        grid[x - 1][y] = grid[x][y];
    }

    // moves vehicle 1 position down
    public void moveDown(int x, int y) {
        int length = grid[x][y].getLength();

        grid[x][y + length] = grid[x][y];
        grid[x][y] = null;
    }

    // moves vehicle 1 position up
    public void moveUp(int x, int y) {
        int length = grid[x][y].getLength();

        grid[x][y + length - 1] = null;
        grid[x][y - 1] = grid[x][y];
    }

    public boolean moveRightIsPossible(int x, int y) {
        boolean direction = grid[x][y].getDirection();
        int length = grid[x][y].getLength();

        return direction && x + length < size && grid[x + length][y] == null;
    }

    public boolean moveLeftIsPossible(int x, int y) {
        boolean direction = grid[x][y].getDirection();

        return direction && x > 0 && grid[x - 1][y] == null;
    }

    public boolean moveDownIsPossible(int x, int y) {
        boolean direction = grid[x][y].getDirection();
        int length = grid[x][y].getLength();

        return !direction && y + length < size && grid[x][y + length] == null;
    }

    public boolean moveUpIsPossible(int x, int y) {
        boolean direction = grid[x][y].getDirection();

        return !direction && y > 0 && grid[x][y - 1] == null;
    }

    // check whether the red car is in front of the exit
    public boolean goalReached() {
        return grid[size - 1][size/2 - 1] != null && grid[size - 1][size / 2 - 1].getNumber() == 1;
    }

    // prints out representation of grid
    public void printGrid() {
            for (int y = 0; y < size; y++){
                for (int x = 0; x < size; x++){
                if (grid[x][y] == null) {
                    System.out.print("0 ");
                } else {
                    System.out.print(grid[x][y].getNumber() + " ");
                }
            }
            System.out.println();
        }
    }
}
