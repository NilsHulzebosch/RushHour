package com.company;

public class Move {

    private int previous_x;
    private int previous_y;
    private int new_x;
    private int new_y;

    public Move(int previous_x, int previous_y, int new_x, int new_y) {
        this.previous_x = previous_x;
        this.previous_y = previous_y;
        this.new_x = new_x;
        this.new_y = new_y;
    }

    public String toString() {
        return previous_x + "," + previous_y + ":" + new_x + "," + new_y;
    }



}
