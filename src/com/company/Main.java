package com.company;

public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid(6);
        grid.addNumber(2,3,5);

        grid.moveLeft(2,3);
        grid.moveLeft(1,3);
        grid.moveRight(0,3);

        grid.printGrid();
    }
}