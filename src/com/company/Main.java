package com.company;

public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid(6);

        Vehicle hor_car = new Vehicle(true, 2);
        Vehicle hor_truck = new Vehicle(true, 3);
        Vehicle ver_car = new Vehicle(false, 2);
        Vehicle ver_truck = new Vehicle(false, 3);

        grid.addVehicle(hor_car, 1, 2);
        grid.addVehicle(ver_truck, 5, 0);

        grid.moveRight(hor_car, 1, 2);
        grid.moveDown(ver_truck, 5, 0);

        grid.printGrid();
    }
}