package org.example.classes;

import static org.example.classes.runner.Runner.managerInputOutput;

public class Coordinates {
    private long x; //Максимальное значение поля: 108
    private long y; //Максимальное значение поля: 20

    public Coordinates() {
        managerInputOutput.writeLineIO("Ввод Coordinates\n");
        setX();
        setY();
    }

    public Coordinates(long x, long y) {
        this.x = x;
        this.y = y;
    }

    private void setX() {
        managerInputOutput.writeLineIO("Введите X для Coordinates : ");

        while (true) {
            String input = managerInputOutput.readLineIO().trim();

            if (input.isEmpty()) {
                managerInputOutput.writeLineIO("X не может быть пустым\n");
                managerInputOutput.writeLineIO("Введите X для Coordinates : ");
                continue;
            }

            try {
                long x = Long.parseLong(input);
                if (0 < x && x <= 108) {
                    this.x = x;
                    break;
                } else {
                    managerInputOutput.writeLineIO("X находится в пределах от 0 до 108\n");
                    managerInputOutput.writeLineIO("Введите X для Coordinates : ");
                }
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("X должно быть типа long\n");
                managerInputOutput.writeLineIO("Введите X для Coordinates : ");
            }
        }
    }

    private void setY() {
        managerInputOutput.writeLineIO("Введите Y для Coordinates : ");

        while (true) {
            String input = managerInputOutput.readLineIO().trim();

            if (input.isEmpty()) {
                managerInputOutput.writeLineIO("Y не может быть пустым\n");
                managerInputOutput.writeLineIO("Введите Y для Coordinates : ");
                continue;
            }

            try {
                long y = Long.parseLong(input);
                if (0 < y && y <= 20) {
                    this.y = y;
                    break;
                } else {
                    managerInputOutput.writeLineIO("Y находится в пределах от 0 до 20\n");
                    managerInputOutput.writeLineIO("Введите Y для Coordinates : ");
                }
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Y должно быть типа long\n");
                managerInputOutput.writeLineIO("Введите Y для Coordinates : ");
            }
        }
    }

    public long getX() {
        return this.x;
    }

    public long getY() {
        return this.y;
    }
}