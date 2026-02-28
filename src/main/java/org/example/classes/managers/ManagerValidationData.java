package org.example.classes.managers;

import org.example.enums.Colors;
import org.example.classes.Route;
import org.example.classes.Coordinates;
import org.example.classes.Location;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

import static org.example.classes.runner.Runner.managerInputOutput;

public class ManagerValidationData {

    public boolean validateCSVFields(String[] fields, int lineNumber) {
        if (fields.length != 12) {
            managerInputOutput.writeLineIO("Строка " + lineNumber + ": должно быть 12 полей\n", Colors.RED);
            return false;
        }

        try {
            long id = Long.parseLong(fields[0].trim());
            if (id <= 0) {
                managerInputOutput.writeLineIO("Строка " + lineNumber + ": id должен быть > 0\n", Colors.RED);
                return false;
            }


            String name = fields[1].trim();
            if (name.isEmpty()) {
                managerInputOutput.writeLineIO("Строка " + lineNumber + ": name не пустой\n", Colors.RED);
                return false;
            }

            long x = Long.parseLong(fields[2].trim());
            if (x <= 0 || x > 108) {
                managerInputOutput.writeLineIO("Строка " + lineNumber + ": X от 1 до 108\n", Colors.RED);
                return false;
            }

            long y = Long.parseLong(fields[3].trim());
            if (y <= 0 || y > 20) {
                managerInputOutput.writeLineIO("Строка " + lineNumber + ": Y от 1 до 20\n", Colors.RED);
                return false;
            }

            try {
                ZonedDateTime.parse(fields[4].trim());
            } catch (DateTimeParseException e) {
                managerInputOutput.writeLineIO("Строка " + lineNumber + ": неверная дата\n", Colors.RED);
                return false;
            }

            Float.parseFloat(fields[5].trim().replace(',', '.'));
            Double.parseDouble(fields[6].trim().replace(',', '.'));
            Integer.parseInt(fields[7].trim().replace(',', '.'));

            Float.parseFloat(fields[8].trim().replace(',', '.'));
            Double.parseDouble(fields[9].trim().replace(',', '.'));
            Integer.parseInt(fields[10].trim().replace(',', '.'));

            int dist = Integer.parseInt(fields[11].trim());
            if (dist <= 1) {
                managerInputOutput.writeLineIO("Строка " + lineNumber + ": distance > 1\n", Colors.RED);
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Строка " + lineNumber + ": ошибка в числах\n", Colors.RED);
            return false;
        }
    }

    public Route validateFromFile() {
        String name = managerInputOutput.readLineIO();
        if (name == null || name.trim().isEmpty()) {
            managerInputOutput.writeLineIO("Ошибка: name не может быть пустым\n");
            return null;
        }

        long coordx, coordy;

        String coordinatesX = managerInputOutput.readLineIO();
        if (coordinatesX == null) return null;
        try {
            coordx = Long.parseLong(coordinatesX.trim());
            if (coordx > 108) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: X coordinates должно быть от -922372036854775808 и не больше 108 и быть целым\n");
            return null;
        }

        String coordinatesY = managerInputOutput.readLineIO();
        if (coordinatesY == null) return null;
        try {
            coordy = Long.parseLong(coordinatesY.trim());
            if (coordy > 108) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: Y coordinates должно быть от -922372036854775808 и не больше 108 и быть целым\n");
            return null;
        }

        float locaXFrom;
        Double locaYFrom;
        int locaZFrom;
        String locationFromX = managerInputOutput.readLineIO();
        if (locationFromX == null) return null;
        try {
            locaXFrom = Float.parseFloat(locationFromX.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: X location from должно быть числом от " + Float.MIN_VALUE + " до " + Float.MAX_VALUE + "\n");
            return null;
        }

        String locationFromY = managerInputOutput.readLineIO();
        if (locationFromY == null) return null;
        try {
            locaYFrom = Double.parseDouble(locationFromY.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: Y location from должно быть числом от " + Double.MIN_VALUE + " до " + Double.MAX_VALUE + "\n");
            return null;
        }

        String locationFromZ = managerInputOutput.readLineIO();
        if (locationFromZ == null) return null;
        try {
            locaZFrom = Integer.parseInt(locationFromZ.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: Z location from должно быть целым числом от " + Integer.MIN_VALUE + " до " + Integer.MAX_VALUE + "\n");
            return null;
        }

        float locaXTo;
        Double locaYTo;
        int locaZTo;
        String locationToX = managerInputOutput.readLineIO();
        if (locationToX == null) return null;
        try {
            locaXTo = Float.parseFloat(locationToX.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: X location to должно быть числом от " + Float.MIN_VALUE + " до " + Float.MAX_VALUE + "\n");
            return null;
        }

        String locationToY = managerInputOutput.readLineIO();
        if (locationToY == null) return null;
        try {
            locaYTo = Double.parseDouble(locationToY.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: Y location to должно быть числом от " + Double.MIN_VALUE + " до " + Double.MAX_VALUE + "\n");
            return null;
        }

        String locationToZ = managerInputOutput.readLineIO();
        if (locationToZ == null) return null;
        try {
            locaZTo = Integer.parseInt(locationToZ.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: Z location to должно быть целым числом от " + Integer.MIN_VALUE + " до " + Integer.MAX_VALUE + "\n");
            return null;
        }

        int dist;
        String distance = managerInputOutput.readLineIO();
        if (distance == null) return null;
        try {
            dist = Integer.parseInt(distance.trim());
            if (dist <= 1) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            managerInputOutput.writeLineIO("Ошибка: distance должно быть целым числом больше 1 и меньше " + Integer.MAX_VALUE + "\n");
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
        Location locationFrom = new Location(validateSetLocationX("From"), validateSetLocationY("From"), validateSetLocationZ("From"));
        Location locationTo = new Location(validateSetLocationX("To"), validateSetLocationY("To"), validateSetLocationZ("To"));
        int distance = validateSetDistance();
        return new Route(name, coordinates, locationFrom, locationTo, distance);
    }

    public Route validateFromInput(long id, ZonedDateTime time) {
        String name = validateSetName();
        Coordinates coordinates = new Coordinates(validateSetCoordinatesX(), validateSetCoordinatesY());
        Location locationFrom = new Location(validateSetLocationX("From"), validateSetLocationY("From"), validateSetLocationZ("From"));
        Location locationTo = new Location(validateSetLocationX("To"), validateSetLocationY("To"), validateSetLocationZ("To"));
        int distance = validateSetDistance();
        return new Route(id, name, coordinates, time, locationFrom, locationTo, distance);
    }

    public Route validateFromInput(long id) {
        String name = validateSetName();
        Coordinates coordinates = new Coordinates(validateSetCoordinatesX(), validateSetCoordinatesY());
        Location locationFrom = new Location(validateSetLocationX("From"), validateSetLocationY("From"), validateSetLocationZ("From"));
        Location locationTo = new Location(validateSetLocationX("To"), validateSetLocationY("To"), validateSetLocationZ("To"));
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
                if (x <= 108) return x;
                else managerInputOutput.writeLineIO("X до 108\n");
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Ошибка: X coordinates должно быть от -922372036854775808 и не больше 108 и быть целым\n");
            }
            managerInputOutput.writeLineIO("Введите X для Coordinates : ");
        }
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
                if (y <= 20) return y;
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Ошибка: Y coordinates должно быть больше 20 и быть целым\n");
            }
            managerInputOutput.writeLineIO("Введите Y для Coordinates : ");
        }
    }

    public float validateSetLocationX(String a) {
        managerInputOutput.writeLineIO("Введите X для Location" + a + ": ");
        while (true) {
            String input = managerInputOutput.readLineIO().trim();
            if (input.isEmpty()) {
                managerInputOutput.writeLineIO("X не может быть пустым\n");
                managerInputOutput.writeLineIO("Введите X для Location" + a + " : ");
                continue;
            }
            try {
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("X должно быть числом в диапазоне от " + Float.MIN_VALUE + " до " + Float.MAX_VALUE + "\n");
                managerInputOutput.writeLineIO("Введите X для Location" + a + " : ");
            }
        }
    }

    public Double validateSetLocationY(String a) {
        managerInputOutput.writeLineIO("Введите Y для Location" + a + ": ");
        while (true) {
            String input = managerInputOutput.readLineIO().trim();
            if (input.isEmpty()) {
                managerInputOutput.writeLineIO("Y не может быть пустым\n");
                managerInputOutput.writeLineIO("Введите Y для Location" + a + " : ");
                continue;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Y должно быть числом в диапазоне от " + Double.MIN_VALUE + " до " + Double.MAX_VALUE + "\n");
                managerInputOutput.writeLineIO("Введите Y для Location" + a + " : ");
            }
        }
    }

    public Integer validateSetLocationZ(String a) {
        managerInputOutput.writeLineIO("Введите Z для Location" + a + ": ");
        while (true) {
            String input = managerInputOutput.readLineIO().trim();
            if (input.isEmpty()) {
                managerInputOutput.writeLineIO("Z не может быть пустым\n");
                managerInputOutput.writeLineIO("Введите Z для Location" + a + " : ");
                continue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Z должно быть целым числом в диапазоне от " + Integer.MIN_VALUE + " до " + Integer.MAX_VALUE + "\n");
                managerInputOutput.writeLineIO("Введите Z для Location" + a + " : ");
            }
        }
    }

    private int validateSetDistance() {
        managerInputOutput.writeLineIO("Введите distance : ");
        while (true) {
            if (managerInputOutput.hasNextIntIO()) {
                int distance = managerInputOutput.nextIntIO();
                managerInputOutput.readLineIO();
                if (distance > 1) return distance;
                else managerInputOutput.writeLineIO("DISTANCE должно быть больше 1\n");
            } else {
                managerInputOutput.readLineIO();
                managerInputOutput.writeLineIO("DISTANCE должно быть целым и в диапазоне от 2" + " до " + Integer.MAX_VALUE + "\n");
            }
            managerInputOutput.writeLineIO("Введите distance : ");
        }
    }
}