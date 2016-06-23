package com.company;

public class GripperPosition {

    public double x;
    public double y;
    public double z;
    public double roll_angle;
    public double gripper_angle;

    // constructor
    public GripperPosition(double x, double y, double z, double roll_angle, double gripper_angle) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.roll_angle = roll_angle;
        this.gripper_angle = gripper_angle;
    }

    @Override
    public String toString() {
        return (String.valueOf(x) + " " + String.valueOf(y) + " " +
                String.valueOf(z) + " " + String.valueOf(roll_angle) + " " +
                String.valueOf(gripper_angle) + " ");
    }
}
