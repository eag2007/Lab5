package org.example.classes;

public class Location {
    private float x;
    private Double y; //Поле не может быть null
    private int z;

    public Location() {
        setX();
        setY();
        setZ();
    }

    public Location(float x, Double y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private void setX() {
    }

    private void setY() {
    }

    private void setZ() {
    }

    public float getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}