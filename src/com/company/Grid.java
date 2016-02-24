package com.company;

public class Grid {

    private int size;
    private int[][] grid;
    private Vehicle[] vehicles;

    private int car_number = 1;

    // constructor
    public Grid(int size, int amount_of_cars) {
        this.size = size;
        this.grid = new int[size][size];
        this.vehicles = new Vehicle[amount_of_cars + 1];
    }

    // adds vehicle to grid and array of all vehicles
    public void addVehicle(Vehicle vehicle, int x, int y) {
        for (int i = 0; i < vehicle.getLength(); i++) {
            if (vehicle.getDirection()) {
                grid[x + i][y] = car_number;
            } else {
                grid[x][y + i] = car_number;
            }
        }
        vehicle.addY(y);
        vehicle.addX(x);
        vehicles[car_number] = vehicle;
        car_number++;
    }

    // moves vehicle 1 position right
    /*
     * First check whether x+vehicle.getLength() passes the grid size (to avoid ArrayIndexOutOfBoundsException).
     * Also make sure it's the first car_number of the car and not the middle/last one, to avoid
     * cars being slashed into pieces (like this: 110000 -> NOT 100100 but 011000).
     * Also check whether the next index is indeed a 'free' spot (value 0).
     */
    public void moveRight(int car_nr) {
        int x = vehicles[car_nr].getX();
        int y = vehicles[car_nr].getY();
        int length = vehicles[car_nr].getLength();

        if (x + length < size && (x == 0 || grid[x - 1][y] != grid[x][y])) {
            if (grid[x + length][y] == 0) {
                grid[x + length][y] = grid[x][y];
                grid[x][y] = 0;
            }
        }
        vehicles[car_nr].addX(x + 1);
    }

    // moves vehicle 1 position left
    /*
     * First check whether x-1 passes the grid size (to avoid ArrayIndexOutOfBoundsException).
     * Also make sure it's the first car_number of the car and not the middle/last one, to avoid
     * cars being slashed into pieces (like this: 110000 -> NOT 100100 but 011000).
     * Also check whether the next index is indeed a 'free' spot (value 0).
     */
    public void moveLeft(int car_nr) {
        int x = vehicles[car_nr].getX();
        int y = vehicles[car_nr].getY();
        int length = vehicles[car_nr].getLength();

        if (x > 0 && grid[x - 1][y] != grid[x][y]) {
            if (grid[x - 1][y] == 0) {
                grid[x + length - 1][y] = 0;
                grid[x - 1][y] = grid[x][y];
            }
        }
        vehicles[car_nr].addX(x - 1);
    }

    // moves vehicle 1 position down
        /*
     * First check whether y+vehicle.getLength() passes the grid size (to avoid ArrayIndexOutOfBoundsException).
     * Also make sure it's the first car_number of the car and not the middle/last one, to avoid
     * cars being slashed into pieces (like this: 110000 -> NOT 100100 but 011000).
     * Also check whether the next index is indeed a 'free' spot (value 0).
     */
    public void moveDown(int car_nr) {
        int x = vehicles[car_nr].getX();
        int y = vehicles[car_nr].getY();
        int length = vehicles[car_nr].getLength();

        if (y + length < size && (y == 0 || grid[x][y - 1] != grid[x][y])) {
            if (grid[x][y + length] == 0) {
                grid[x][y + length] = grid[x][y];
                grid[x][y] = 0;
            }
        }
        vehicles[car_nr].addY(y + 1);
    }

    // moves vehicle 1 position up
    /*
     * First check whether y-1 passes the grid size (to avoid ArrayIndexOutOfBoundsException).
     * Also make sure it's the first car_number of the car and not the middle/last one, to avoid
     * cars being slashed into pieces (like this: 110000 -> NOT 100100 but 011000).
     * Also check whether the next index is indeed a 'free' spot (value 0).
     */
    public void moveUp(int car_nr) {
        int x = vehicles[car_nr].getX();
        int y = vehicles[car_nr].getY();
        int length = vehicles[car_nr].getLength();

        if (y > 0 && grid[x][y - 1] != grid[x][y]) {
            if (grid[x][y - 1] == 0) {
                grid[x][y + length - 1] = 0;
                grid[x][y - 1] = grid[x][y];
            }
        }
        vehicles[car_nr].addY(y - 1);
    }

    // check whether the red car is in front of the exit
    public boolean goalReached() {
        return vehicles[1].getX() == size - 2;
    }

    // prints out representation of grid
    public void printGrid() {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(grid[j][i] + " ");
            }
            System.out.println();
        }
    }
}
