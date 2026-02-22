package org.example.classes;

import static org.example.classes.runner.Runner.managerInputOutput;

public class Location implements Comparable<Location>{
    private float x;
    private Double y; //Поле не может быть null
    private int z;

    @Override
    public int compareTo(Location o) {
        int xCompare = Float.compare(this.x, o.x);
        if (xCompare != 0) {
            return xCompare;
        }

        int yCompare = Double.compare(this.y, o.y);
        if (yCompare != 0) {
            return yCompare;
        }

        return Integer.compare(this.z, o.z);
    }

    public Location(float x, Double y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return this.x;
    }

    public Double getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }
}