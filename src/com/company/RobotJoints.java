package com.company;

import java.util.Hashtable;
import java.util.Vector;

public class RobotJoints {
    private Hashtable<String, Integer> jointNames;
    private Vector<RobotJoints.Joint> joints;
    private static int LEFT = 0;
    private static int RIGHT = 1;

    public RobotJoints() {
        this.initJointNames();
        this.joints = new Vector();
        this.joints.add(((Integer)this.jointNames.get("zed")).intValue(), new RobotJoints.Joint(90.0D, 0.0D, 67.5D, 1082.0D, -3478.0D, 0.0D, 0.0D, 925.0D, "zed"));
        this.joints.add(((Integer)this.jointNames.get("shoulder")).intValue(), new RobotJoints.Joint(0.0D, 0.0D, 253.5D, 95.0D, -2630.0D, 2630.0D, -90.0D, 90.0D, "shoulder"));
        this.joints.add(((Integer)this.jointNames.get("elbow")).intValue(), new RobotJoints.Joint(0.0D, 0.0D, 253.5D, 80.0D, -2630.0D, 2206.0D, -180.0D, 151.0D, "elbow"));
        this.joints.add(((Integer)this.jointNames.get("yaw")).intValue(), new RobotJoints.Joint(0.0D, -90.0D, 0.0D, 0.0D, -1071.0D, 1071.0D, -110.0D, 110.0D, "yaw"));
        this.joints.add(((Integer)this.jointNames.get("pitch")).intValue(), new RobotJoints.Joint(0.0D, 90.0D, 0.0D, 0.0D, -2642.0D, 108.0D, -98.0D, 4.0D, "pitch"));
        this.joints.add(((Integer)this.jointNames.get("roll")).intValue(), new RobotJoints.Joint(0.0D, 0.0D, 0.0D, 189.0D, -3560.0D, 4882.0D, -132.0D, 181.0D, "roll"));
        this.joints.add(((Integer)this.jointNames.get("gripper")).intValue(), new RobotJoints.Joint(0.0D, 0.0D, 0.0D, 0.0D, -30.0D, 1200.0D, 0.0D, 90.0D, "gripper"));
    }

    private void initJointNames() {
        this.jointNames = new Hashtable();
        this.jointNames.put("zed", new Integer(0));
        this.jointNames.put("shoulder", new Integer(1));
        this.jointNames.put("elbow", new Integer(2));
        this.jointNames.put("yaw", new Integer(3));
        this.jointNames.put("pitch", new Integer(4));
        this.jointNames.put("roll", new Integer(5));
        this.jointNames.put("gripper", new Integer(6));
    }

    public RobotJoints.Joint get(int var1) {
        return (RobotJoints.Joint)this.joints.get(var1);
    }

    public RobotJoints.Joint get(String var1) {
        return (RobotJoints.Joint)this.joints.get(((Integer)this.jointNames.get(var1)).intValue());
    }

    private static int config(GripperPosition var0) {
        return var0.x >= 0.0D?RIGHT:LEFT;
    }

    public static void correctCartesian(Vector<GripperPosition> var0) {
        for(int var1 = 0; var1 < var0.size(); ++var1) {
            GripperPosition var2 = (GripperPosition)var0.elementAt(var1);
            correctCartesian(var2);
        }

        System.err.println("Using new correction code, not tested yet. ");
    }

    public static void correctCartesian(GripperPosition var0) {
        int var1 = config(var0);
        correctCartesian(var0, var1);
    }

    public static void correctCartesian(GripperPosition var0, int var1) {
        short var2 = 157;
        byte var3 = -10;
        byte var4 = -11;
        byte var5 = -15;
        byte var6 = 14;
        if(var1 == RIGHT) {
            if(-var0.x < (double)var3) {
                var0.x += (double)var3;
            }

            var0.y += (double)var4;
        } else if(var1 == LEFT) {
            if(-var0.x > (double)var5) {
                var0.x += (double)var5;
            }

            var0.y += (double)var6;
        } else {
            System.err.println("unknown config " + var1 + " of arm");
        }

        var0.z -= (double)var2;
    }

    public String toString() {
        String var1 = new String();

        for(int var2 = 0; var2 < this.joints.size(); ++var2) {
            RobotJoints.Joint var3 = (RobotJoints.Joint)this.joints.get(var2);
            var1 = var1 + "joint " + var2 + " " + var3 + "\n";
        }

        return var1;
    }

    public class Joint {
        public double theta;
        public double alpha;
        public double a;
        public double d;
        public double ECmin;
        public double ECmax;
        public double Rmin;
        public double Rmax;
        public String name;

        public Joint() {
            this.name = "nameless joint";
            this.theta = 0.0D;
            this.alpha = 0.0D;
            this.a = 0.0D;
            this.d = 0.0D;
            this.ECmin = 0.0D;
            this.ECmax = 0.0D;
            this.Rmin = 0.0D;
            this.Rmax = 0.0D;
        }

        public Joint(double var2, double var4, double var6, double var8, double var10, double var12, double var14, double var16, String var18) {
            this.theta = var2;
            this.alpha = var4;
            this.a = var6;
            this.d = var8;
            this.ECmin = var10;
            this.ECmax = var12;
            this.Rmin = var14;
            this.Rmax = var16;
            this.name = var18;
        }

        public String toString() {
            return new String("[" + this.name + ": theta " + this.theta + " alpha " + this.alpha + " a " + this.a + " d " + this.d + " ECmin " + this.ECmin + " ECmax " + this.ECmax + " Rmin " + this.Rmin + " Rmax " + this.Rmax + "]");
        }
    }
}
