package com.company;

import javafx.geometry.Point3D;

public class Joint {

    private RobotJoints robotJoints = new RobotJoints();
    Point3D wrist;

    private double zed;
    private double shoulder;
    private double elbow;
    private double yaw = 0;
    private double pitch = -90;
    private double roll = 0;
    private double grip;

    // constructor
    // inverse kinematics used to calculate joint angles
    public Joint(GripperPosition gp) {
        /* correct for errors in the arm*/
        // if left on the board then assume left-hand configuration
        // if right on the board then assume right-hand configuration
        if (gp.x < 0) {
            RobotJoints.correctCartesian(gp, 0);
        } else {
            RobotJoints.correctCartesian(gp, 1);
        }

        grip = gp.gripper_angle;

        wrist = new Point3D(gp.x, gp.y, gp.z + robotJoints.get("roll").d);

        zed = wrist.getZ() + robotJoints.get("shoulder").d +
                robotJoints.get("elbow").d;

        // left-handed coordinates
        double x = wrist.getY();
        double y = wrist.getX();

        // shoulder length
        double l1 = robotJoints.get("shoulder").a;
        // elbow length
        double l2 = robotJoints.get("elbow").a;

        double c2 = (x * x + y * y - l1 * l1 - l2 * l2) / (2 * l1 * l2);

        double s2 = Math.sqrt(1 - c2 * c2);

        // shoulder angle (degrees)
        double theta1 = Math.toDegrees(Math.atan2(y, x) -
                Math.atan2(l2 * s2, l1 + l2 * c2));
        // elbow angle (degrees)
        double theta2 = Math.toDegrees(Math.atan2(s2, c2));

        // shoulder angle must be between -90 and 90 degrees
        if (90 < theta1 || theta1 < -90) {
            theta1 += theta2;
            theta2 *= -1;
        }

        shoulder = theta1;
        elbow = theta2;
    }

    @Override
    public String toString() {
        return String.valueOf(zed) + " " + String.valueOf(shoulder) + " " +
                String.valueOf(elbow) + " " + String.valueOf(yaw) + " " +
                String.valueOf(pitch) + " " + String.valueOf(roll) + " " +
                String.valueOf(grip) + " ";
    }

}
