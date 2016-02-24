package com.company;

/**
 * Created by Sjors on 23-2-2016.
 */
public class Vehicle {

    // true = horizontal
    // false = vertical
    private boolean direction;
    private int length;
    private int x;
    private int y;

    // constructor
    public Vehicle(boolean direction, int length) {
        this.length = length;
        this.direction = direction;
    }

    // returns direction (horizontal or vertical)
    public boolean getDirection() {
        return direction;
    }

    // returns length
    public int getLength() {
        return length;
    }

    // adds x position
    public void addX(int x) {
        this.x = x;
    }

    // adds y position
    public void addY(int y) {
        this.y = y;
    }

    // returns x position
    public int getX() {
        return x;
    }

    // returns y position
    public int getY() {
        return y;
    }
}
