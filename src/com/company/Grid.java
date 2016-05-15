package com.company;
import java.util.ArrayList;
import java.util.HashSet;

public class Grid {

    private int size;
    private Vehicle[][] grid;
    private int vehicle_number = 1;
    private Grid parent_grid;

    private int path_size = 0;
    private int path_estimate = 0;
    private int score = 0;

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

    // check whether the red car is in front of the exit (goal position)
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
                            //new_grid.calculatePathEstimate();
                            new_grid.calculateScore();
                            array_list.add(new_grid);

                            new_grid2.moveLeft(x, y);
                            //new_grid2.calculatePathEstimate();
                            new_grid2.calculateScore();
                            array_list.add(new_grid2);
                        } else if (moveRightIsPossible(x, y)) {
                            new_grid.moveRight(x, y);
                            //new_grid.calculatePathEstimate();
                            new_grid.calculateScore();
                            array_list.add(new_grid);
                        } else if (moveLeftIsPossible(x, y)) {
                            new_grid.moveLeft(x, y);
                            //new_grid.calculatePathEstimate();
                            new_grid.calculateScore();
                            array_list.add(new_grid);
                        } else if (moveDownIsPossible(x, y) && moveUpIsPossible(x, y)) {
                            new_grid.moveDown(x, y);
                            //new_grid.calculatePathEstimate();
                            new_grid.calculateScore();
                            array_list.add(new_grid);

                            new_grid2.moveUp(x, y);
                            //new_grid2.calculatePathEstimate();
                            new_grid2.calculateScore();
                            array_list.add(new_grid2);
                        } else if (moveDownIsPossible(x, y)) {
                            new_grid.moveDown(x, y);
                            //new_grid.calculatePathEstimate();
                            new_grid.calculateScore();
                            array_list.add(new_grid);
                        } else if (moveUpIsPossible(x, y)) {
                            new_grid.moveUp(x, y);
                            //new_grid.calculatePathEstimate();
                            new_grid.calculateScore();
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

    public int getScore() {
        return score;
    }



    /* This heuristic calculates the amount of steps needed
     * to move the red car to it's goal position (blocking cars not taken into account). */
    public void distanceToGoalPosition_heuristic(int x1) {
        score += (size - x1) * 10;      // 20 for puzzle 6
    }

    /* This heuristic calculates how many cars are blocking the red car,
     * [if they are double blocked itself, an extra point is counted]. */
    public void blockingCars_heuristic(int x1, int goal_y) {
        for (int x = x1 + 2; x < size; x++) {
            if (grid[x][goal_y] != null) {
                score += 20;    // 20 for puzzle 6
                HashSet<Integer> carNumbers = new HashSet<>();
                carNumbers.add(1);
                score += blockingCarsCalculator(x, goal_y, 1, carNumbers)*20;

                //System.out.println(blockingCarsCalculator(i, goal_y, 1, carNumbers));
                //System.out.println(path_estimate-current_estimate);
            }
        }
    }

    /* This heuristic checks if there is a clear path from the red car to the goal position,
     * i.e. there are no cars between the red car and the goal position. If this is true,
     * a big minimum score is given (so the path will be clear), and the distance from red car
     * to goal is given points, so it will move the red car towards the goal. */
    public void clearPath_heuristic(int x1, int goal_y) {
        boolean pathIsClear = true;
        for(int x = x1+2; x < size; x++) {
            if(grid[x][goal_y] != null) {
                pathIsClear = false;
            }
        }
        if(pathIsClear) {
            score -= 400;
            score += (size - x1) * 40;
        }
    }

    /* This heuristic looks at the area around the red car and adds points for every field
     * that is non-empty (so the more crowded the surrounding area, the more points). */
    public void surroundingCars_heuristic(int x1, int goal_y) {
        // calculate how far ahead (in the x direction) it must look for surrounding cars
        int maximumX;
        if (size - x1 > 5) {
            maximumX = x1 + 5;
        } else if (size - x1 > 2) {
            maximumX = x1 + 2;
        } else if (size - x1 == 2) {
            maximumX = x1 + 1;
        } else {
            maximumX = x1;
        }

        // calculate the amount of non-empty fields in the area
        for (int y = goal_y - 2; y < goal_y + 3; y++) {
            for (int x = x1; x <= maximumX; x++) {
                if (grid[x][y] != null) {
                    score += 6;             // (4 for puzzle 5)
                }
            }
        }
    }

    /* This heuristic calculates the total move freedom of all cars combined,
     * (i.e. the total possible moves from all cars added up). This will be substracted
     * from a constant, so the bigger the move freedom, the lower the points. */
    public int moveFreedom_heuristic() {
        int moveFreedom = 0;

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (grid[x][y] != null) {
                    boolean direction = grid[x][y].getDirection();
                    // only if object is the most left or top of the entire vehicle
                    if ((direction && (x == 0 || (x > 0 && grid[x][y] != grid[x - 1][y]))) ||
                            (!direction && (y == 0 || (y > 0 && grid[x][y] != grid[x][y - 1])))) {
                        if (moveRightIsPossible(x, y)) {
                            moveFreedom++;
                        }
                        if (moveLeftIsPossible(x, y)) {
                            moveFreedom++;
                        }
                        if (moveUpIsPossible(x, y)) {
                            moveFreedom++;
                        }
                        if (moveDownIsPossible(x, y)) {
                            moveFreedom++;
                        }
                    }
                }
            }
        }

        return (30-moveFreedom)*7;     // for puzzle 5: 8 or 9
    }

    /* This heuristic looks at how the cars are divided on the grid. It divides the grid into
     * four quadrants and looks at how each quadrant is filled with cars. If the cars are too
     * skewed, it gives points, so it will have the tendency to get a more equal distribution. */
    public void quadrantDistribution_heuristic() {
        int coordinate = size/2;
        int endCoordinate = size-1;

        int firstQuadrant = calculateFreeSpace(0, 0, coordinate, coordinate);
        int secondQuadrant = calculateFreeSpace(coordinate, 0, endCoordinate, coordinate);
        int thirdQuadrant = calculateFreeSpace(0, coordinate, coordinate, endCoordinate);
        int fourthQuadrant = calculateFreeSpace(coordinate, coordinate, endCoordinate, endCoordinate);

        /*
        // for puzzle 5
        if(firstQuadrant > secondQuadrant) {
            mobility += 50;
        }
        if(thirdQuadrant > fourthQuadrant) {
            mobility += 50;
        }
        if(firstQuadrant > thirdQuadrant) {
            mobility += 20;
        }
        */

        if(secondQuadrant != 0 && firstQuadrant % secondQuadrant > 4) {
            score += 20;
        }
        if(thirdQuadrant != 0 && firstQuadrant % thirdQuadrant > 4) {
            score += 20;
        }
        if(secondQuadrant != 0 && fourthQuadrant % secondQuadrant > 4) {
            score += 20;
        }
        if(thirdQuadrant != 0 && fourthQuadrant % thirdQuadrant > 4) {
            score += 20;
        }
    }

    public int calculateFreeSpace(int startX, int startY, int endX, int endY) {
        int freeSpace = 0;

        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                if (grid[x][y] == null) {
                    freeSpace++;
                }
            }
        }

        return freeSpace;
    }

    /* This heuristic gives points for every horizontal car on the last column. */
    public void horizontalCars_heuristic() {
        for(int y = 0; y < size; y++) {
            if (grid[size - 1][y] != null && grid[size - 1][y].getDirection()) {
                score += 20;
            }
        }
    }

    /* This heuristic detects whether the red car is caught in a cycle. This means that
     * when it has to move forward (to the goal position), it is blocked by some sequence
     * of cars, which is blocked again by the red car. So it has to move backwards.
     * Whenever a cycle occurs, it gives points. */
    public void detectCycles_heuristic(int x, int y) {
        score += 200;
    }



    // calculates an inadmissible score based on several (inadmissible) heuristics
    // the lower the score, the better the board, the earlier it appears in the PQ
    public void calculateScore() {
        /* Heuristic 1 & 2 necessary for puzzle 6 (20 & 20 malus points)
         */

        // before applying heuristics, get the current position of the red car

        // first get row (y-pos) of red car based on board size
        int goal_y;
        if (size % 2 == 0) {
            goal_y = size / 2 - 1;
        } else {
            goal_y = size / 2;
        }

        // then find the x-position of the red car
        int x1 = 0;
        while (grid[x1][goal_y] == null || grid[x1][goal_y].getNumber() != 1) {
            x1++;
        }

        // distanceToGoalPosition heuristic
        distanceToGoalPosition_heuristic(x1);

        // blockingCars heuristic
        blockingCars_heuristic(x1, goal_y);

        // clearPath heuristic
        clearPath_heuristic(x1, goal_y);

        // quadrantDistribution heuristic
        quadrantDistribution_heuristic();

        // surroundingCars heuristic
        surroundingCars_heuristic(x1, goal_y);

        // horizontalCars heuristic
        //horizontalCars_heuristic();

        // moveFreedom heuristic
        score += moveFreedom_heuristic();

    }

    public void calculatePathEstimate() {
        //System.out.println(calculateMoveFreedom());
        path_estimate += moveFreedom_heuristic();

        // get row (y-pos) of red car based on board size
        int goal_y;
        if (size % 2 == 0) {
            goal_y = size / 2 - 1;
        } else {
            goal_y = size / 2;
        }

        // find the x-position of the red car
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
                int current_estimate = path_estimate;
                HashSet<Integer> carNumbers = new HashSet<>();
                carNumbers.add(1);
                path_estimate += blockingCarsCalculator(i, goal_y, 1, carNumbers);
                //System.out.println(blockingCarsCalculator(i, goal_y, 1, carNumbers));
                //System.out.println(path_estimate-current_estimate);
            }
        }
    }

    // calculates how many cars are blocking the red car (using recursion)
    public int blockingCarsCalculator(int x, int y, int currentAmount, HashSet<Integer> carNumbers) {
        int amountOfBlockingCars = currentAmount;
        int number = grid[x][y].getNumber();
        boolean direction = grid[x][y].getDirection();

        //System.out.println("carNumbers: " + carNumbers);
        //System.out.println("currentAmount: " + currentAmount);

        // set all booleans to false
        boolean leftIsBlocked = false;
        boolean rightIsBlocked = false;
        boolean topIsBlocked = false;
        boolean bottomIsBlocked = false;

        // if the number is 1, the red car is blocking some (sequence of) cars that are (/is) blocking the red car
        // so we HAVE to move the red car to the left, so check the blocking cars on the left
        if(number == 1) {

            // find out if there is a car at the left
            int x3 = x;
            while (x3 > 0 && grid[x3][y] != null && grid[x3][y].getNumber() == number) {
                x3--;
            }
            if (grid[x3][y] == null) {
                leftIsBlocked = false;
            } else {
                leftIsBlocked = true;
                //System.out.println("x: " + x3 + ". y: " + y);
                //System.out.println("number: " + number);
                //System.out.println("amountOfBlockingCars: " + amountOfBlockingCars);
                if(grid[x3][y].getNumber() != 1) {
                    amountOfBlockingCars += blockingCarsCalculator(x3, y, 0, carNumbers);
                }
            }
        }


        // if this car is not used to calculate blocking cars yet, add it to the HashSet and proceed
        // this is to prevent infinite loops
        if (!carNumbers.contains(number)) {
            carNumbers.add(number);

            // input car is horizontal
            if (direction) {
                // find out if there is a car at the left
                int x1 = x;
                while (x1 > 0 && grid[x1][y] != null && grid[x1][y].getNumber() == number) {
                    x1--;
                }
                if (grid[x1][y] == null) {
                    leftIsBlocked = false;
                } else {
                    leftIsBlocked = true;
                    //amountOfBlockingCars += blockingCarsCalculator(x1, y, 0, carNumbers);
                }


                // find out if there is a car at the right
                int x2 = x;
                while (x2 < size - 1 && grid[x2][y] != null && grid[x2][y].getNumber() == number) {
                    x2++;
                }
                if (grid[x2][y] == null) {
                    rightIsBlocked = false;
                } else {
                    rightIsBlocked = true;
                    //amountOfBlockingCars += blockingCarsCalculator(x2, y, 0, carNumbers);
                }

                // if left and right are blocked, increment the amount of blocking cars by 1,
                // if it is not the car itself, use recursion to find more blocking cars
                if (leftIsBlocked && rightIsBlocked) {
                    amountOfBlockingCars++;

                    if (grid[x1][y].getNumber() != number) {
                        amountOfBlockingCars += blockingCarsCalculator(x1, y, 0, carNumbers);
                    }
                    if (grid[x2][y].getNumber() != number) {
                        amountOfBlockingCars += blockingCarsCalculator(x2, y, 0, carNumbers);
                    }
                }
            }

            if (!direction) {
                // find out if there is a car at the top
                int y1 = y;
                while (y1 > 0 && grid[x][y1] != null && grid[x][y1].getNumber() == number) {
                    y1--;
                }
                if (grid[x][y1] == null) {
                    topIsBlocked = false;
                } else {
                    topIsBlocked = true;
                    //amountOfBlockingCars += blockingCarsCalculator(x, y1, 0, carNumbers);
                }

                // find out if there is a car at the bottom
                int y2 = y;
                while (y2 < size - 1 && grid[x][y2] != null && grid[x][y2].getNumber() == number) {
                    y2++;
                }
                if (grid[x][y2] == null) {
                    bottomIsBlocked = false;
                } else {
                    bottomIsBlocked = true;
                    //amountOfBlockingCars += blockingCarsCalculator(x, y2, 0, carNumbers);
                }

                if (topIsBlocked && bottomIsBlocked) {
                    amountOfBlockingCars++;

                    if (grid[x][y1].getNumber() != number) {
                        amountOfBlockingCars += blockingCarsCalculator(x, y1, 0, carNumbers);
                    }
                    if (grid[x][y2].getNumber() != number) {
                        amountOfBlockingCars += blockingCarsCalculator(x, y2, 0, carNumbers);
                    }
                }
            }
        }

        // if two sides of a car are blocked, it will take AT LEAST one move (extra) to move the current car
        //if((leftIsBlocked && rightIsBlocked) || (topIsBlocked && bottomIsBlocked)) {
        //    amountOfBlockingCars++;
        //}

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
