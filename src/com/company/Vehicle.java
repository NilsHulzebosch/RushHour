package com.company;

/**
 * Created by Sjors on 23-2-2016.
 */
public class Vehicle {

    // true = horizontal
    // false = vertical
    private boolean direction;
    private int length;

    public Vehicle(boolean direction, int length) {
        this.length = length;
        this.direction = direction;
    }

    public boolean getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }
}
