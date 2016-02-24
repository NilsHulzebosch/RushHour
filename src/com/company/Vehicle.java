package com.company;

public class Vehicle {

    // true = horizontal
    // false = vertical
    private boolean direction;
    private int length;
    private int x;
    private int y;

    // constructor
    public Vehicle(boolean direction, int length, int x, int y) {
        this.direction = direction;
        this.length = length;
        this.x = x;
        this.y = y;
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
