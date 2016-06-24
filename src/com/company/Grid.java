package com.company;
import java.util.ArrayList;

/* Table of Contents
 * 1. Private instance variables / constructors / get
 * 2. Methods for adding/moving vehicles & making child grids
 * 3. Methods for calculating score / path estimate
 * 4. Methods for the heuristics
 * 5. Methods for the path / grid (visualisation)
 * 6. Methods for the hash set
 */

public class Grid {

    /* ****************************************************
     * 1. Private instance variables / constructors / get *
     **************************************************** */

    private int size;
    private Vehicle[][] grid;
    private int vehicle_number = 1;
    private Grid parent_grid;

    private int path_size = 0;
    private int score = 0;

    private int red_x;
    private int red_y;

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

    public int getGridSize() {
        return size;
    }

    public Grid getParent() {
        return parent_grid;
    }

    public int getPathSize() {
        return path_size;
    }

    public int getScore() {
        return score;
    }

    // compares previous Grid and this Grid to find Move
    public Move getMove() {
        Vehicle[][] previous_grid = getParent().getGrid();

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (grid[x][y] == null && previous_grid[x][y] != null) {
                    // if horizontal
                    if (previous_grid[x][y].getDirection()) {
                        return new Move(previous_grid[x][y], x, y, x + 1, y);

                    // if vertical
                    } else {
                        return new Move(previous_grid[x][y], x, y, x, y + 1);
                    }
                } else if (grid[x][y] != null && previous_grid[x][y] == null) {
                    // if horizontal
                    if (grid[x][y].getDirection()) {
                        return new Move(grid[x][y], x + 1, y, x, y);

                    // if vertical
                    } else {
                        return new Move(grid[x][y], x, y + 1, x, y);
                    }
                }
            }
        }
        return null;
    }


    /* ************************************************************
     * 2. Methods for adding/moving vehicles & making child grids *
     ************************************************************ */

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

    // checks if moveRight is possible
    public boolean moveRightIsPossible(int x, int y) {
        boolean direction = grid[x][y].getDirection();
        int length = grid[x][y].getLength();

        return direction && x + length < size && grid[x + length][y] == null;
    }

    // checks if moveLeft is possible
    public boolean moveLeftIsPossible(int x, int y) {
        boolean direction = grid[x][y].getDirection();

        return direction && x > 0 && grid[x - 1][y] == null;
    }

    // checks if moveDown is possible
    public boolean moveDownIsPossible(int x, int y) {
        boolean direction = grid[x][y].getDirection();
        int length = grid[x][y].getLength();

        return !direction && y + length < size && grid[x][y + length] == null;
    }

    // checks if moveUp is possible
    public boolean moveUpIsPossible(int x, int y) {
        boolean direction = grid[x][y].getDirection();

        return !direction && y > 0 && grid[x][y - 1] == null;
    }

    // check whether the red car is in front of the exit (goal position)
    public boolean goalReached() {
        if (size % 2 == 0) {
            return (grid[size - 1][size / 2 - 1] != null &&
                    grid[size - 1][size / 2 - 1].getNumber() == 1);
        } else {
            return (grid[size - 1][size / 2] != null &&
                    grid[size - 1][size / 2].getNumber() == 1);
        }
    }

    // generates all the possible children grids (i.e. moves) based on the current grid
    public ArrayList<Grid> generateAllChildren() {
        ArrayList<Grid> children_list = new ArrayList<>();

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {

                if (grid[x][y] != null) {

                    boolean direction = grid[x][y].getDirection();
                    // only if object is the most left or top of the entire vehicle
                    if ((direction && (x == 0 || (x > 0 && grid[x][y] != grid[x - 1][y]))) ||
                            (!direction && (y == 0 || (y > 0 && grid[x][y] != grid[x][y - 1])))) {

                        if (moveRightIsPossible(x, y) && moveLeftIsPossible(x, y)) {
                            Grid new_grid = new Grid(this);
                            new_grid.moveRight(x, y);
                            children_list.add(new_grid);

                            Grid new_grid2 = new Grid(this);
                            new_grid2.moveLeft(x, y);
                            children_list.add(new_grid2);
                        } else if (moveRightIsPossible(x, y)) {
                            Grid new_grid = new Grid(this);
                            new_grid.moveRight(x, y);
                            children_list.add(new_grid);
                        } else if (moveLeftIsPossible(x, y)) {
                            Grid new_grid = new Grid(this);
                            new_grid.moveLeft(x, y);
                            children_list.add(new_grid);
                        } else if (moveDownIsPossible(x, y) && moveUpIsPossible(x, y)) {
                            Grid new_grid = new Grid(this);
                            new_grid.moveDown(x, y);
                            children_list.add(new_grid);

                            Grid new_grid2 = new Grid(this);
                            new_grid2.moveUp(x, y);
                            children_list.add(new_grid2);
                        } else if (moveDownIsPossible(x, y)) {
                            Grid new_grid = new Grid(this);
                            new_grid.moveDown(x, y);
                            children_list.add(new_grid);
                        } else if (moveUpIsPossible(x, y)) {
                            Grid new_grid = new Grid(this);
                            new_grid.moveUp(x, y);
                            children_list.add(new_grid);
                        }
                    }
                }
            }
        }

        for (Grid child : children_list) {
            child.calculateAStarScore();
            //child.calculateInadmissableScore();
        }

        return children_list;
    }


    /* ******************************************************
     * 3. Methods for calculating the score / path estimate *
     ****************************************************** */

    /* calculates an inadmissible score based on several (inadmissible) heuristics
     * the lower the score, the better */
    public void calculateInadmissableScore() {
        getRedCarPosition();

        // adds current path size to score
        //score += path_size * 25;

        // distanceToGoalPosition: takes the x position of the red car and the score weight
        score += distanceToGoalPosition_heuristic(red_x, 20);

        // blockingCars: takes the x and y position of the red car and the score weight
        // (the direct (first) and indirect (second) blocking cars score)
        score += blockingCars_heuristic(red_x, red_y, 20, 20);

        // clearPath: takes the x and y position of the red car and the score weight
        score += clearPath_heuristic(red_x, red_y, -400, 40);

        // quadrantDistribution: takes the score weight and the boundary
        //score += quadrantDistribution_heuristic(30, 3);

        // surroundingCars: takes the x and y position of the red car and the score weight
        score += surroundingCars_heuristic(red_x, red_y, 16);

        // moveFreedom: takes the score weight
        //score += moveFreedom_heuristic(8);

        // blockingCarBehind: takes the x and y position of the red car and the score weight
        //score += blockingCarBehind_heuristic(red_x, red_y, 200);
    }

    // calculates score for A* algorithm
    public void calculateAStarScore() {
        getRedCarPosition();

        // adds current path size to score
        score = path_size;

        // distanceToGoalPosition: takes the x position of the red car and the score weight (1)
        score += distanceToGoalPosition_heuristic(red_x, 1);

        // blockingCars: takes the x and y position of the red car and the score weight
        // (1 for the direct (first) and 1 for the indirect (second) blocking cars)
        score += blockingCars_heuristic(red_x, red_y, 1, 1);
    }

    // finds the x and y position of the red car (used for the heuristics)
    public void getRedCarPosition() {
        if (size % 2 == 0) {
            red_y = size / 2 - 1;
        } else {
            red_y = size / 2;
        }

        while (grid[red_x][red_y] == null || grid[red_x][red_y].getNumber() != 1) {
            red_x++;
        }
    }


     /* ******************************
     * 4. Methods for the heuristics *
     ******************************* */

    /* This heuristic calculates the amount of steps needed
     * to move the red car to it's goal position (blocking cars not taken into account). */
    public int distanceToGoalPosition_heuristic(int x1, int weight) {
        return (size - x1 - 2) * weight;
    }

    /* This heuristic calculates how many cars are blocking the red car,
     * [if they are double blocked themselves, an extra point is counted]. */
    public int blockingCars_heuristic(int x1, int goal_y, int weight, int indirectWeight) {
        int blockingCarsScore = 0;
        for (int x = x1 + 2; x < size; x++) {
            if (grid[x][goal_y] != null) {
                blockingCarsScore += weight;

                if(indirectWeight > 0) {
                    int number = grid[x][goal_y].getNumber();
                    int y1 = goal_y;
                    while (y1 > 0 && grid[x][y1] != null && grid[x][y1].getNumber() == number) {
                        y1--;
                    }
                    if (grid[x][y1] != null) {
                        int y2 = goal_y;
                        while (y2 < size - 1 && grid[x][y2] != null &&
                                grid[x][y2].getNumber() == number) {
                            y2++;
                        }
                        if (grid[x][y2] != null) {
                            blockingCarsScore += indirectWeight;
                        }
                    }
                }
            }
        }
        return blockingCarsScore;
    }

    /* This heuristic checks if there is a clear path from the red car to the goal position,
     * i.e. there are no cars between the red car and the goal position. If this is true,
     * a big minimum score is given (so the path will be clear), and the distance from red car
     * to goal is given points, so it will move the red car towards the goal. */
    public int clearPath_heuristic(int x1, int goal_y, int negativeWeight, int positiveWeight) {
        boolean pathIsClear = true;
        for(int x = x1+2; x < size; x++) {
            if(grid[x][goal_y] != null) {
                pathIsClear = false;
            }
        }
        if(pathIsClear) {
            return (negativeWeight + (size - x1) * positiveWeight);
        }
        return 0;
    }

    /* This heuristic looks at the area around the red car and adds points for every field
     * that is non-empty (so the more crowded the surrounding area, the more points). */
    public int surroundingCars_heuristic(int x1, int goal_y, int weight) {
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
        int surroundingCarsScore = 0;
        for (int y = goal_y - 2; y < goal_y + 3; y++) {
            for (int x = x1; x <= maximumX; x++) {
                if (grid[x][y] != null) {
                    surroundingCarsScore += weight;
                }
            }
        }
        return surroundingCarsScore;
    }

    /* This heuristic calculates the total move freedom of all cars combined,
     * (i.e. the total possible moves from all cars added up). This will be substracted
     * from a constant, so the bigger the move freedom, the lower the points. */
    public int moveFreedom_heuristic(int weight) {
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

        return (30 - moveFreedom) * weight;
    }

    /* This heuristic looks at how the cars are divided on the grid. It divides the grid into
     * four quadrants and looks at how each quadrant is filled with cars. If the cars are too
     * skewed, it gives points, so it will have the tendency to get a more equal distribution. */
    public int quadrantDistribution_heuristic(int weight, int boundary) {
        int quadrantDistributionScore = 0;
        int coordinate = size/2;
        int endCoordinate = size-1;

        int firstQuadrant = calculateFreeSpace(0, 0, coordinate, coordinate);
        int secondQuadrant = calculateFreeSpace(coordinate, 0, endCoordinate, coordinate);
        int thirdQuadrant = calculateFreeSpace(0, coordinate, coordinate, endCoordinate);
        int fourthQuadrant = calculateFreeSpace(coordinate, coordinate,
                endCoordinate, endCoordinate);

        if(secondQuadrant != 0 && firstQuadrant % secondQuadrant > boundary) {
            quadrantDistributionScore += weight;
        }
        if(thirdQuadrant != 0 && firstQuadrant % thirdQuadrant > boundary) {
            quadrantDistributionScore += weight;
        }
        if(secondQuadrant != 0 && fourthQuadrant % secondQuadrant > boundary) {
            quadrantDistributionScore += weight;
        }
        if(thirdQuadrant != 0 && fourthQuadrant % thirdQuadrant > boundary) {
            quadrantDistributionScore += weight;
        }
        return quadrantDistributionScore;
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

    public int blockingCarBehind_heuristic(int x1, int goal_y, int weight) {
        if(x1 > 0 && grid[x1 - 1][goal_y] != null) {
            return weight;
        }
        return 0;
    }


    /* ************************************************
     * 5. Methods for the path / grid (visualisation) *
     ************************************************ */

    // returns path of current grid
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


     /* ****************************
     * 6. Methods for the hash set *
     ***************************** */

    // calculates key for hashing
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

    // compares grids by running through the Vehicle matrix
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
    }
}
