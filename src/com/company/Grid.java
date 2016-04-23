package com.company;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Grid {

    private int size;
    private Vehicle[][] grid;
    private int vehicle_number = 1;
    private Grid parent_grid;

    private int path_size = 0;
    private int path_estimate = 0;

    // constructor
    public Grid(int size) {
        this.size = size;
        this.grid = new Vehicle[size][size];
    }

    // constructor
    public Grid(Grid previous) {
        this.size = previous.size;
        this.parent_grid = previous;
        this.path_size = previous.path_size + 1;

        this.grid = new Vehicle[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[j][i] = previous.grid[j][i];
            }
        }
    }

    public Vehicle[][] getGrid() {
        return grid;
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
        if (size % 2 == 0) {
            return grid[size - 1][size / 2 - 1] != null && grid[size - 1][size / 2 - 1].getNumber() == 1;
        } else {
            return grid[size - 1][size / 2] != null && grid[size - 1][size / 2].getNumber() == 1;
        }
    }

    public ArrayList<Grid> generateAllChildren() {
        ArrayList<Grid> array_list = new ArrayList<>();

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (grid[x][y] != null) {

                    boolean direction = grid[x][y].getDirection();
                    // only if object is the most left or top of the entire vehicle
                    if ((direction && (x == 0 || (x > 0 && grid[x][y] != grid[x - 1][y]))) ||
                            (!direction && (y == 0 || (y > 0 && grid[x][y] != grid[x][y - 1])))) {

                        Grid new_grid = new Grid(this);
                        Grid new_grid2 = new Grid(this);
                        if (moveRightIsPossible(x, y) && moveLeftIsPossible(x, y)) {
                            new_grid.moveRight(x, y);
                            new_grid.calculatePathEstimate();
                            array_list.add(new_grid);

                            new_grid2.moveLeft(x, y);
                            new_grid2.calculatePathEstimate();
                            array_list.add(new_grid2);
                        } else if (moveRightIsPossible(x, y)) {
                            new_grid.moveRight(x, y);
                            new_grid.calculatePathEstimate();
                            array_list.add(new_grid);
                        } else if (moveLeftIsPossible(x, y)) {
                            new_grid.moveLeft(x, y);
                            new_grid.calculatePathEstimate();
                            array_list.add(new_grid);
                        } else if (moveDownIsPossible(x, y) && moveUpIsPossible(x, y)) {
                            new_grid.moveDown(x, y);
                            new_grid.calculatePathEstimate();
                            array_list.add(new_grid);

                            new_grid2.moveUp(x, y);
                            new_grid2.calculatePathEstimate();
                            array_list.add(new_grid2);
                        } else if (moveDownIsPossible(x, y)) {
                            new_grid.moveDown(x, y);
                            new_grid.calculatePathEstimate();
                            array_list.add(new_grid);
                        } else if (moveUpIsPossible(x, y)) {
                            new_grid.moveUp(x, y);
                            new_grid.calculatePathEstimate();
                            array_list.add(new_grid);
                        }
                    }
                }
            }
        }
        return array_list;
    }

    public Grid getParent() {
        return parent_grid;
    }

    public int getPathSize() {
        return path_size;
    }

    public int getPathEstimate() {
        return path_estimate;
    }

    public void calculatePathEstimate() {
        // get row of red car based on board size
        int goal_y;
        if (size % 2 == 0) {
            goal_y = size/2 - 1;
        } else {
            goal_y = size/2;
        }

        // find the position of the red car
        int x = 0;
        while (grid[x][goal_y] == null || grid[x][goal_y].getNumber() != 1) {
            x++;
        }

        // distance from red car to goal position
        path_estimate = size - x - 2;

        // (minimum) amount of blocking cars
        for (int i = x + 2; i < size; i++) {
            if (grid[i][goal_y] != null) {
                path_estimate += 1;

                HashSet<Integer> carNumbers = new HashSet<>();
                path_estimate += blockingCarsCalculator(i, goal_y, 1, carNumbers);
                //System.out.println(blockingCarsCalculator(i, goal_y, 1, carNumbers));

            }
        }
    }

    // calculates how many cars are blocking the red car (using recursion)
    // LET OP: lange code, moet indien mogelijk worden ingekort
    public int blockingCarsCalculator(int x, int y, int currentAmount, HashSet<Integer> carNumbers) {
        int amountOfBlockingCars = currentAmount;
        int number = grid[x][y].getNumber();
        boolean direction = grid[x][y].getDirection();

        // set all booleans to false
        boolean leftIsBlocked = false;
        boolean rightIsBlocked = false;
        boolean topIsBlocked = false;
        boolean bottomIsBlocked = false;

        // if this car is not used to calculate blocking cars yet, add it to the HashSet and proceed
        // this is to prevent infinite loops
        if (!carNumbers.contains(number) && currentAmount < 15) {
            carNumbers.add(number);

            // input car is horizontal
            if (direction) {

                // check the left side
                if (x - 1 >= 0) {
                    if (grid[x - 1][y] == null) {
                        leftIsBlocked = false;
                    } else {
                        if (grid[x - 1][y] != null &&
                                grid[x - 1][y].getNumber() != number) {
                            leftIsBlocked = true;
                            amountOfBlockingCars += blockingCarsCalculator(x - 1, y, 0, carNumbers);
                        } else if (grid[x - 1][y] != null &&
                                grid[x - 1][y].getNumber() == number) {

                            // check one step further to the left
                            if (x - 2 >= 0) {
                                if (grid[x - 2][y] == null) {
                                    leftIsBlocked = false;
                                } else {
                                    if (grid[x - 2][y] != null &&
                                            grid[x - 2][y].getNumber() != number) {
                                        leftIsBlocked = true;
                                        amountOfBlockingCars += blockingCarsCalculator(x - 2, y, 0, carNumbers);
                                    } else if (grid[x - 2][y] != null &&
                                            grid[x - 2][y].getNumber() == number) {

                                        // check one step further to the left (for cars with length 3)
                                        if (x - 3 >= 0) {
                                            if (grid[x - 3][y] == null) {
                                                leftIsBlocked = false;
                                            } else {
                                                leftIsBlocked = true;
                                                amountOfBlockingCars += blockingCarsCalculator(x - 3, y, 0, carNumbers);
                                            }
                                        } else {
                                            leftIsBlocked = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (x == 0) {
                    leftIsBlocked = true;
                }

                // check the right side
                if (x + 1 < size) {
                    if (grid[x + 1][y] == null) {
                        rightIsBlocked = false;
                    } else {
                        if (grid[x + 1][y] != null &&
                                grid[x + 1][y].getNumber() != number) {
                            rightIsBlocked = true;
                            amountOfBlockingCars += blockingCarsCalculator(x + 1, y, 0, carNumbers);
                        } else if (grid[x + 1][y] != null &&
                                grid[x + 1][y].getNumber() == number) {

                            // check one step further to the right
                            if (x + 2 < size) {
                                if (grid[x + 2][y] == null) {
                                    rightIsBlocked = false;
                                } else {
                                    if (grid[x + 2][y] != null &&
                                            grid[x + 2][y].getNumber() != number) {
                                        rightIsBlocked = true;
                                        amountOfBlockingCars += blockingCarsCalculator(x + 2, y, 0, carNumbers);
                                    } else if (grid[x + 2][y] != null &&
                                            grid[x + 2][y].getNumber() == number) {

                                        // check one step further to the right (for cars with length 3)
                                        if (x + 3 < size) {
                                            if (grid[x + 3][y] == null) {
                                                rightIsBlocked = false;
                                            } else {
                                                rightIsBlocked = true;
                                                amountOfBlockingCars += blockingCarsCalculator(x + 3, y, 0, carNumbers);
                                            }
                                        } else {
                                            rightIsBlocked = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (x + 1 == size) {
                    rightIsBlocked = true;
                }
            }

            // input car is vertical
            if (!direction) {

                // check the top side
                if (y - 1 >= 0) {
                    if (grid[x][y - 1] == null) {
                        topIsBlocked = false;
                    } else {
                        if (grid[x][y - 1] != null &&
                                grid[x][y - 1].getNumber() != number) {
                            topIsBlocked = true;
                            amountOfBlockingCars += blockingCarsCalculator(x, y - 1, 0, carNumbers);
                        } else if (grid[x][y - 1] != null &&
                                grid[x][y - 1].getNumber() == number) {

                            // check one step further to the top
                            if (y - 2 >= 0) {
                                if (grid[x][y - 2] == null) {
                                    topIsBlocked = false;
                                } else {
                                    if (grid[x][y - 2] != null &&
                                            grid[x][y - 2].getNumber() != number) {
                                        topIsBlocked = true;
                                        amountOfBlockingCars += blockingCarsCalculator(x, y - 2, 0, carNumbers);
                                    } else if (grid[x][y - 2] != null &&
                                            grid[x][y - 2].getNumber() == number) {

                                        // check one step further to the top (for cars with length 3)
                                        if (y - 3 >= 0) {
                                            if (grid[x][y - 3] == null) {
                                                topIsBlocked = false;
                                            } else {
                                                topIsBlocked = true;
                                                amountOfBlockingCars += blockingCarsCalculator(x, y - 3, 0, carNumbers);
                                            }
                                        } else {
                                            topIsBlocked = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (y == 0) {
                    topIsBlocked = true;
                }


                // check the down side
                if (y + 1 < size) {
                    if (grid[x][y + 1] == null) {
                        bottomIsBlocked = false;
                    } else {
                        if (grid[x][y + 1] != null &&
                                grid[x][y + 1].getNumber() != number) {
                            bottomIsBlocked = true;
                            amountOfBlockingCars += blockingCarsCalculator(x, y + 1, 0, carNumbers);
                        } else if (grid[x][y + 1] != null &&
                                grid[x][y + 1].getNumber() == number) {

                            // check one step further to the top
                            if (y + 2 < size) {
                                if (grid[x][y + 2] == null) {
                                    bottomIsBlocked = false;
                                } else {
                                    if (grid[x][y + 2] != null &&
                                            grid[x][y + 2].getNumber() != number) {
                                        bottomIsBlocked = true;
                                        amountOfBlockingCars += blockingCarsCalculator(x, y + 2, 0, carNumbers);
                                    } else if (grid[x][y + 2] != null &&
                                            grid[x][y + 2].getNumber() == number) {

                                        // check one step further to the top (for cars with length 3)
                                        if (y + 3 < size) {
                                            if (grid[x][y + 3] == null) {
                                                bottomIsBlocked = false;
                                            } else {
                                                bottomIsBlocked = true;
                                                amountOfBlockingCars += blockingCarsCalculator(x, y + 3, 0, carNumbers);
                                            }
                                        } else {
                                            bottomIsBlocked = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (y + 1 == size) {
                    bottomIsBlocked = true;
                }
            }
        }

        // if two sides of a car are blocked, it will take AT LEAST one move (extra) to move the current car
        if((leftIsBlocked && rightIsBlocked) ||
                (topIsBlocked && bottomIsBlocked)) {
            amountOfBlockingCars++;
        }

        return amountOfBlockingCars;
    }

    public ArrayList<Grid> getPath() {
        ArrayList<Grid> path = new ArrayList<>();
        path.add(this);
        Grid child = this;

        while (child.getParent() != null) {
            Grid parent = child.getParent();
            path.add(parent);
            child = parent;
        }

        return path;
    }

    // prints out representation of grid
    public void printGrid() {
            for (int y = 0; y < size; y++){
                for (int x = 0; x < size; x++){
                if (grid[x][y] == null) {
                    System.out.print("0  ");
                } else {
                    if(grid[x][y].getNumber() < 10) {
                        System.out.print(grid[x][y].getNumber() + "  ");
                    } else {
                        System.out.print(grid[x][y].getNumber() + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    public int hashCode() {
        int key = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[j][i] != null) {
                    key += grid[j][i].getNumber() * i * j * (grid[j][i].getLength() + i * j);
                    if (grid[j][i].getDirection()) {
                        key += i + j;
                    } else {
                        key += i * j;
                    }
                } else {
                    key *= i + j;
                }
            }
        }
        return key;
    }

    public boolean equals(Object o) {
        Grid child_grid = (Grid) o;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.getGrid()[j][i] != child_grid.getGrid()[j][i]) {
                    return false;
                }
            }
        }
        return true;

        //return Arrays.deepEquals(this.getGrid(), child_grid.getGrid());
    }
}
