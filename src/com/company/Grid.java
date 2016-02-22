package com.company;

/**
 * Created by Sjors on 11-2-2016.
 */
public class Grid {

    private int size;
    private int[][] array;

    public Grid(int size) {
        this.size = size;
        array = new int[size][size];
    }

    public void addNumber(int x, int y, int number){
        array[x][y] = number;
    }

    public void moveRight(int x, int y) {
        array[x + 1][y + 1] = array[x][y];
        array[x][y] = 0;
    }

    public void moveLeft(int x, int y) {
        array[x - 1][y - 1] = array[x][y];
        array[x][y] = 0;
    }

    public void printGrid() {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(array[j][i] + " ");
            }
            System.out.println();
        }
    }
}
