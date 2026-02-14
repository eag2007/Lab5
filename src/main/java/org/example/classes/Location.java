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

    public Location(String s) {
        managerInputOutput.writeLineIO("Ввод Location" + s + "\n");
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
        managerInputOutput.writeLineIO("Введите X для Location : ");

        float x;
        while (true) {
            String input = managerInputOutput.readLineIO().trim();

            if (input.isEmpty()) {
                managerInputOutput.writeLineIO("X не может быть пустым\n");
                managerInputOutput.writeLineIO("Введите X для Location : ");
                continue;
            }

            try {
                x = Float.parseFloat(input);
                this.x = x;
                break;
            } catch (NumberFormatException e) {
                try {
                    x = Integer.parseInt(input);
                    this.x = x;
                    break;
                } catch (NumberFormatException e2) {
                    managerInputOutput.writeLineIO("X должно быть числом (int или float)\n");
                    managerInputOutput.writeLineIO("Введите X для Location : ");
                }
            }
        }
    }

    private void setY() {
        managerInputOutput.writeLineIO("Введите Y для Location : ");

        while (true) {
            String input = managerInputOutput.readLineIO().trim();

            if (input.isEmpty()) {
                managerInputOutput.writeLineIO("Y не может быть пустым\n");
                managerInputOutput.writeLineIO("Введите Y для Location : ");
                continue;
            }

            try {
                this.y = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Y должно быть числом (int, float или double)\n");
                managerInputOutput.writeLineIO("Введите Y для Location : ");
            }
        }
    }

    private void setZ() {
        managerInputOutput.writeLineIO("Введите Z для Location : ");

        while (true) {
            String input = managerInputOutput.readLineIO().trim();

            if (input.isEmpty()) {
                managerInputOutput.writeLineIO("Z не может быть пустым\n");
                managerInputOutput.writeLineIO("Введите Z для Location : ");
                continue;
            }

            try {
                this.z = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Z должно быть целым числом (int)\n");
                managerInputOutput.writeLineIO("Введите Z для Location : ");
            }
        }
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