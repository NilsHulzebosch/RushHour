package com.company;
import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {

    public static void main(String[] args) {
        Grid grid = new Grid(6);

        Vehicle hor_car = new Vehicle(true, 2);
        Vehicle hor_truck = new Vehicle(true, 3);
        Vehicle ver_car = new Vehicle(false, 2);
        Vehicle ver_truck = new Vehicle(false, 3);

        grid.addVehicle(hor_car, 0, 2);
        grid.addVehicle(ver_truck, 4, 0);

        grid.whipeScreen();
        grid.printGrid();
        grid.delay(3000);

        grid.moveDown(ver_truck, 4, 0);
        grid.moveRight(hor_car, 0, 2);

        grid.whipeScreen();
        grid.printGrid();
        grid.delay(3000);

        grid.moveUp(ver_truck, 4, 1);
        grid.moveLeft(hor_car, 1, 2);
        //grid.moveDown(ver_truck, 4, 0);

        grid.whipeScreen();
        grid.printGrid();
        grid.delay(3000);

    }
}
