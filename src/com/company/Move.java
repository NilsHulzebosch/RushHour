package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Move {

    private static double SQUARE_SIZE = 64;
    private static double BOARD_SIZE = 384;
    private static double BOARD_THICKNESS = 8;
    private static double TOP_LEFT_X = 210 - BOARD_SIZE;
    private static double TOP_LEFT_Y = 120;
    private static double OPEN_GRIP = 30;
    private static double CLOSED_GRIP = 0;
    private static double GRAB_HEIGHT = 55;
    private static double SAFE_HEIGHT = 90;
    private static double ANGLE = 0;

    // vehicle that is being moved
    private Vehicle vehicle;
    public Point from = new Point();
    public Point to = new Point();

    // constructor
    public Move(Vehicle vehicle, int from_x, int from_y, int to_x, int to_y) {
        this.vehicle = vehicle;
        from.x = from_x;
        from.y = from_y;
        to.x = to_x;
        to.y = to_y;
    }

    // sets to Point
    public void setToPoint(Point to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return from.x + "," + from.y + " to " + to.x + "," + to.y;
    }

    // translates column and row coordinates to cartesian coordinates
    private Point2D toCartesian(Point coords) {
        double x = 0;
        double y = 0;
        if (vehicle.getLength() == 2) {
            // if horizontal
            if (vehicle.getDirection()) {
                x = TOP_LEFT_X + (coords.x + 1) * SQUARE_SIZE;
                y = TOP_LEFT_Y + (coords.y + 0.5) * SQUARE_SIZE;

            // if vertical
            } else {
                x = TOP_LEFT_X + (coords.x + 0.5) * SQUARE_SIZE;
                y = TOP_LEFT_Y + (coords.y + 1) * SQUARE_SIZE;
            }
        } else if (vehicle.getLength() == 3) {
            // if horizontal
            if (vehicle.getDirection()) {
                x = TOP_LEFT_X + (coords.x + 1.5) * SQUARE_SIZE;
                y = TOP_LEFT_Y + (coords.y + 0.5) * SQUARE_SIZE;

                // if vertical
            } else {
                x = TOP_LEFT_X + (coords.x + 0.5) * SQUARE_SIZE;
                y = TOP_LEFT_Y + (coords.y + 1.5) * SQUARE_SIZE;
            }
        }
        return (new Point2D.Double(x, y));
    }

    // returns this Move's GripperPosition path
    public ArrayList<GripperPosition> getPath() {
        ArrayList<GripperPosition> path = new ArrayList<>();
        GripperPosition temp;

        // from position, safe height, open grip
        Point2D coords = toCartesian(from);
        temp = new GripperPosition(coords.getX(), coords.getY(), BOARD_THICKNESS + SAFE_HEIGHT, ANGLE, OPEN_GRIP);
        path.add(temp);

        // from position, grab height, open grip
        temp = new GripperPosition(coords.getX(), coords.getY(), BOARD_THICKNESS + GRAB_HEIGHT, ANGLE, OPEN_GRIP);
        path.add(temp);

        // from position, grab height, closed grip
        temp = new GripperPosition(coords.getX(), coords.getY(), BOARD_THICKNESS + GRAB_HEIGHT, ANGLE, CLOSED_GRIP);
        path.add(temp);

        // to position, grab height, closed grip
        coords = toCartesian(to);
        temp = new GripperPosition(coords.getX(), coords.getY(), BOARD_THICKNESS + GRAB_HEIGHT, ANGLE, CLOSED_GRIP);
        path.add(temp);

        // to position, grab height, open grip
        temp = new GripperPosition(coords.getX(), coords.getY(), BOARD_THICKNESS + GRAB_HEIGHT, ANGLE, OPEN_GRIP);
        path.add(temp);

        // to position, safe height, open grip
        temp = new GripperPosition(coords.getX(), coords.getY(), BOARD_THICKNESS + SAFE_HEIGHT, ANGLE, OPEN_GRIP);
        path.add(temp);

        return path;
    }

}
