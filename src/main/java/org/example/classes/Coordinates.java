package org.example.classes;

import static org.example.classes.runner.Runner.managerInputOutput;

public class Coordinates implements Comparable<Coordinates> {
    private long x; //Максимальное значение поля: 108
    private long y; //Максимальное значение поля: 20

    public Coordinates(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Coordinates o) {
        int xCompare = Long.compare(this.x, o.x);
        if (xCompare != 0) {
            return xCompare;
        }

        return Long.compare(this.y, o.y);
    }

    public long getX() {
        return this.x;
    }

    public long getY() {
        return this.y;
    }
}