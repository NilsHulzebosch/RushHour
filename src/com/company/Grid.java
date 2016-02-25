package com.company;

public class Grid {

    private int size;
    private int[][] grid;
    private Vehicle[] vehicles;

    private int car_number = 1;
    private int amount_of_vehicles;

    // constructor
    public Grid(int size, int amount_of_vehicles) {
        this.size = size;
        this.grid = new int[size][size];
        this.amount_of_vehicles = amount_of_vehicles;
        this.vehicles = new Vehicle[amount_of_vehicles + 1];
    }

    public Grid(Grid previous) {
        this.size = previous.size;
        this.amount_of_vehicles = previous.amount_of_vehicles;

        this.grid = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[j][i] = previous.grid[j][i];
            }
        }

        this.vehicles = new Vehicle[amount_of_vehicles + 1];
        for (int i = 1; i <= amount_of_vehicles; i++) {
            vehicles[i] = previous.vehicles[i];
        }
    }

    // adds vehicle to grid and array of all vehicles
    public void addVehicle(boolean direction, int length, int x, int y) {
        for (int i = 0; i < length; i++) {
            if (direction) {
                grid[x + i][y] = car_number;
            } else {
                grid[x][y + i] = car_number;
            }
        }
        vehicles[car_number] = new Vehicle(direction, length, x, y);
        car_number++;
    }

    // moves vehicle 1 position right
    public Grid moveRight(int car_nr) {
        int x = vehicles[car_nr].getX();
        int y = vehicles[car_nr].getY();
        int length = vehicles[car_nr].getLength();

        grid[x + length][y] = grid[x][y];
        grid[x][y] = 0;
        vehicles[car_nr].addX(x + 1);

        return this;
    }

    // moves vehicle 1 position left
    public Grid moveLeft(int car_nr) {
        int x = vehicles[car_nr].getX();
        int y = vehicles[car_nr].getY();
        int length = vehicles[car_nr].getLength();

        grid[x + length - 1][y] = 0;
        grid[x - 1][y] = grid[x][y];
        vehicles[car_nr].addX(x - 1);

        return this;
    }

    // moves vehicle 1 position down
    public Grid moveDown(int car_nr) {
        int x = vehicles[car_nr].getX();
        int y = vehicles[car_nr].getY();
        int length = vehicles[car_nr].getLength();

        grid[x][y + length] = grid[x][y];
        grid[x][y] = 0;
        vehicles[car_nr].addY(y + 1);

        return this;
    }

    // moves vehicle 1 position up
    public Grid moveUp(int car_nr) {
        int x = vehicles[car_nr].getX();
        int y = vehicles[car_nr].getY();
        int length = vehicles[car_nr].getLength();

        grid[x][y + length - 1] = 0;
        grid[x][y - 1] = grid[x][y];
        vehicles[car_nr].addY(y - 1);

        return this;
    }

    public boolean moveRightIsPossible(int car_nr) {
        int x = vehicles[car_nr].getX();
        int y = vehicles[car_nr].getY();
        int length = vehicles[car_nr].getLength();

        if (x + length < size) {
            return grid[x + length][y] == 0;
        }
        return false;
    }

    public boolean moveLeftIsPossible(int car_nr) {
        int x = vehicles[car_nr].getX();
        int y = vehicles[car_nr].getY();

        if (x > 0) {
            return grid[x - 1][y] == 0;
        }
        return false;
    }

    public boolean moveDownIsPossible(int car_nr) {
        int x = vehicles[car_nr].getX();
        int y = vehicles[car_nr].getY();
        int length = vehicles[car_nr].getLength();

        if (y + length < size) {
            return grid[x][y + length] == 0;
        }
        return false;
    }

    public boolean moveUpIsPossible(int car_nr) {
        int x = vehicles[car_nr].getX();
        int y = vehicles[car_nr].getY();

        if (y > 0) {
            return grid[x][y - 1] == 0;
        }
        return false;
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

/*
private void getPossibleMoves(int car_nr) {
        int x = vehicles[car_nr].getX();
        int y = vehicles[car_nr].getY();
        boolean direction = vehicles[car_nr].getDirection();
        int length = vehicles[car_nr].getLength();

        if (direction) {
            // horizontal

            // move right
            if (grid[x+length][y] == 0) {
                boolean moveRight = true;
            } else {
                boolean moveRight = false;
            }

            // move left
            if (grid[x-1][y] == 0) {
                boolean moveLeft = true;
            } else {
                boolean moveLeft = false;
            }


        } else {
            // vertical

        }
    }
*/
}
