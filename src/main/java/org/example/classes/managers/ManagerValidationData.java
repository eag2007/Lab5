package org.example.classes.managers;

import org.example.classes.Coordinates;
import org.example.classes.Location;
import org.example.classes.Route;

import java.text.NumberFormat;

import static org.example.classes.runner.Runner.managerInputOutput;

public class ManagerValidationData {

    public Route validateFromFile() {
        String name = managerInputOutput.readLineIO();
        String coordinatesX = managerInputOutput.readLineIO();
        String coordinatesY = managerInputOutput.readLineIO();
        String locationFromX = managerInputOutput.readLineIO();
        String locationFromY = managerInputOutput.readLineIO();
        String locationFromZ = managerInputOutput.readLineIO();
        String locationToX = managerInputOutput.readLineIO();
        String locationToY = managerInputOutput.readLineIO();
        String locationToZ = managerInputOutput.readLineIO();
        String distance = managerInputOutput.readLineIO();

        if (name == null || name.trim().isEmpty()) {
            managerInputOutput.writeLineIO("Ошибка: name не может быть пустым");
            return null;
        }

        long coordx, coordy;
        try {
            coordx = Long.parseLong(coordinatesX.trim());
            if (coordx <= 0 || coordx > 108) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: X coordinates должно быть числом от 1 до 108\n");
            return null;
        }

        try {
            coordy = Long.parseLong(coordinatesY.trim());
            if (coordy <= 0 || coordy > 108) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: Y coordinates должно быть числом от 1 до 108\n");
            return null;
        }

        float locaXFrom;
        Double locaYFrom;
        int locaZFrom;

        try {
            locaXFrom = Float.parseFloat(locationFromX.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: X location from должно быть числом\n");
            return null;
        }

        try {
            locaYFrom = Double.parseDouble(locationFromY.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: Y location from должно быть числом\n");
            return null;
        }

        try {
            locaZFrom = Integer.parseInt(locationFromZ.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: Z location from должно быть целым числом\n");
            return null;
        }

        float locaXTo;
        Double locaYTo;
        int locaZTo;

        try {
            locaXTo = Float.parseFloat(locationToX.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: X location to должно быть числом\n");
            return null;
        }

        try {
            locaYTo = Double.parseDouble(locationToY.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: Y location to должно быть числом\n");
            return null;
        }

        try {
            locaZTo = Integer.parseInt(locationToZ.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: Z location to должно быть целым числом\n");
            return null;
        }

        int dist;
        try {
            dist = Integer.parseInt(distance.trim());
            if (dist <= 1) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: distance должно быть целым числом больше 1\n");
            return null;
        }

        Coordinates coordinates = new Coordinates(coordx, coordy);
        Location from = new Location(locaXFrom, locaYFrom, locaZFrom);
        Location to = new Location(locaXTo, locaYTo, locaZTo);

        return new Route(name, coordinates, from, to, dist);
    }

    public Route validateFromInput() {
        String name = validateSetName();
        Coordinates coordinates = new Coordinates(validateSetCoordinatesX(), validateSetCoordinatesY());
        Location locationFrom = new Location(validateSetLocationX(), validateSetLocationY(), validateSetLocationZ());
        Location locationTo = new Location(validateSetLocationX(), validateSetLocationY(), validateSetLocationZ());
        int distance = validateSetDistance();
        return new Route(name, coordinates, locationFrom, locationTo, distance);
    }

    public Route validateFromInput(long id) {
        String name = validateSetName();
        Coordinates coordinates = new Coordinates(validateSetCoordinatesX(), validateSetCoordinatesY());
        Location locationFrom = new Location(validateSetLocationX(), validateSetLocationY(), validateSetLocationZ());
        Location locationTo = new Location(validateSetLocationX(), validateSetLocationY(), validateSetLocationZ());
        int distance = validateSetDistance();
        return new Route(id, name, coordinates, locationFrom, locationTo, distance);
    }

    public String validateSetName() {
        managerInputOutput.writeLineIO("Введите name : ");
        String name = managerInputOutput.readLineIO();

        while (name.isEmpty()) {
            managerInputOutput.writeLineIO("NAME не может быть null\n");
            managerInputOutput.writeLineIO("Введите name : ");
            name = managerInputOutput.readLineIO();
        }
        return name;
    }

    public long validateSetCoordinatesY() {
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
                    return y;
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

    public long validateSetCoordinatesX() {
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
                    return x;
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

    public float validateSetLocationX() {
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
                return x;
            } catch (NumberFormatException e) {
                try {
                    x = Integer.parseInt(input);
                    return x;
                } catch (NumberFormatException e2) {
                    managerInputOutput.writeLineIO("X должно быть числом (int или float)\n");
                    managerInputOutput.writeLineIO("Введите X для Location : ");
                }
            }
        }
    }

    public Double validateSetLocationY() {
        managerInputOutput.writeLineIO("Введите Y для Location : ");

        while (true) {
            String input = managerInputOutput.readLineIO().trim();

            if (input.isEmpty()) {
                managerInputOutput.writeLineIO("Y не может быть пустым\n");
                managerInputOutput.writeLineIO("Введите Y для Location : ");
                continue;
            }

            try {
                Double y = Double.parseDouble(input);
                return y;
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Y должно быть числом (int, float или double)\n");
                managerInputOutput.writeLineIO("Введите Y для Location : ");
            }
        }
    }

    public Integer validateSetLocationZ() {
        managerInputOutput.writeLineIO("Введите Z для Location : ");

        while (true) {
            String input = managerInputOutput.readLineIO().trim();

            if (input.isEmpty()) {
                managerInputOutput.writeLineIO("Z не может быть пустым\n");
                managerInputOutput.writeLineIO("Введите Z для Location : ");
                continue;
            }

            try {
                Integer z = Integer.parseInt(input);
                return z;
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Z должно быть целым числом (int)\n");
                managerInputOutput.writeLineIO("Введите Z для Location : ");
            }
        }
    }

    private int validateSetDistance() {
        managerInputOutput.writeLineIO("Введите distance : ");

        int distance;
        while (true) {
            if (managerInputOutput.hasNextIntIO()) {
                distance = managerInputOutput.nextIntIO();
                managerInputOutput.readLineIO();
                if (distance > 1) {
                    return distance;
                } else {
                    managerInputOutput.writeLineIO("DISTANCE должно быть больше\n");
                }
            } else {
                managerInputOutput.readLineIO();
                managerInputOutput.writeLineIO("DISTANCE должно быть типа Integer\n");
            }

            managerInputOutput.writeLineIO("Введите distance : ");
        }
    }
}
