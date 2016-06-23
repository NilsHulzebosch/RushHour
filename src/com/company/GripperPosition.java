package com.company;

public class GripperPosition {

    private double x;
    private double y;
    private double z;
    private double roll_angle;
    private double gripper_angle;

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
        String string_x = String.valueOf(x);
        String string_y = String.valueOf(y);
        String string_z = String.valueOf(z);
        String string_roll_angle = String.valueOf(roll_angle);
        String string_gripper_angle = String.valueOf(gripper_angle);
        return (string_x + " " + string_y + " " + string_z + " " + string_roll_angle + " " + string_gripper_angle);
    }
}
