package com.company;

public class Vehicle {

    // true = horizontal
    // false = vertical
    private boolean direction;
    private int length;
    private int vehicle_number;

    // constructor
    public Vehicle(boolean direction, int length, int vehicle_number) {
        this.direction = direction;
        this.length = length;
        this.vehicle_number = vehicle_number;
    }

    public Vehicle(Vehicle previous) {
        this.direction = previous.direction;
        this.length = previous.length;
    }

    // returns vehicle number
    public int getNumber() {
        return vehicle_number;
    }

    // returns direction (horizontal or vertical)
    public boolean getDirection() {
        return direction;
    }

    // returns length
    public int getLength() {
        return length;
    }
}
