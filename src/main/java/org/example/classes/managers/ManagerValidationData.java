package org.example.classes.managers;

import org.example.classes.Coordinates;
import org.example.classes.Location;
import org.example.classes.Route;

import java.text.NumberFormat;

import static org.example.classes.runner.Runner.managerInputOutput;

/**
 * Менеджер для валидации вводимых данных и создания объектов {@link Route}.
 * <p>
 * Этот класс отвечает за:
 * <ul>
 *   <li>Валидацию пользовательского ввода в консольном режиме</li>
 *   <li>Валидацию данных из файлов в режиме выполнения скрипта</li>
 *   <li>Создание объектов {@link Route} с проверкой всех ограничений полей</li>
 *   <li>Циклический запрос данных до получения корректных значений</li>
 * </ul>
 *
 * Класс тесно взаимодействует с {@link ManagerInputOutput} для получения данных
 * и вывода сообщений об ошибках пользователю.
 *
 * @author
 * @version 1.0
 * @see Route
 * @see Coordinates
 * @see Location
 * @see ManagerInputOutput
 */
public class ManagerValidationData {

    /**
     * Создает объект {@code Route} из данных, читаемых из файла (скриптовый режим).
     * <p>
     * Метод последовательно читает 10 строк из файла, представляющих все поля объекта {@link Route}.
     * Каждое поле проходит валидацию в соответствии с ограничениями:
     * <ul>
     *   <li>name - не может быть пустым или null</li>
     *   <li>coordinates X - число от 1 до 108</li>
     *   <li>coordinates Y - число от 1 до 108</li>
     *   <li>location X - число с плавающей точкой</li>
     *   <li>location Y - число с плавающей точкой (Double)</li>
     *   <li>location Z - целое число</li>
     *   <li>distance - целое число больше 1</li>
     * </ul>
     * В случае ошибки валидации любого поля метод возвращает {@code null}.
     *
     * @return созданный объект {@code Route} при успешной валидации,
     *         или {@code null} при ошибке в любом поле
     */
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

    /**
     * Создает объект {@code Route} из данных, вводимых пользователем в консоли.
     * <p>
     * Метод последовательно вызывает специализированные методы валидации для каждого поля:
     * <ol>
     *   <li>{@link #validateSetName()} - валидация названия</li>
     *   <li>{@link #validateSetCoordinatesX()} - валидация X координаты</li>
     *   <li>{@link #validateSetCoordinatesY()} - валидация Y координаты</li>
     *   <li>{@link #validateSetLocationX()} - валидация X локации (для from и to)</li>
     *   <li>{@link #validateSetLocationY()} - валидация Y локации (для from и to)</li>
     *   <li>{@link #validateSetLocationZ()} - валидация Z локации (для from и to)</li>
     *   <li>{@link #validateSetDistance()} - валидация расстояния</li>
     * </ol>
     * ID и дата создания генерируются автоматически в конструкторе {@link Route}.
     *
     * @return созданный объект {@code Route} с автоматически сгенерированным ID
     */
    public Route validateFromInput() {
        String name = validateSetName();
        Coordinates coordinates = new Coordinates(validateSetCoordinatesX(), validateSetCoordinatesY());
        Location locationFrom = new Location(validateSetLocationX(), validateSetLocationY(), validateSetLocationZ());
        Location locationTo = new Location(validateSetLocationX(), validateSetLocationY(), validateSetLocationZ());
        int distance = validateSetDistance();
        return new Route(name, coordinates, locationFrom, locationTo, distance);
    }

    /**
     * Создает объект {@code Route} с заданным ID из данных, вводимых пользователем.
     * <p>
     * Используется при обновлении существующего элемента коллекции.
     * В отличие от {@link #validateFromInput()}, этот метод позволяет указать конкретный ID
     * для создаваемого объекта, что необходимо для сохранения идентификатора при обновлении.
     *
     * @param id идентификатор для нового (обновленного) объекта
     * @return созданный объект {@code Route} с указанным ID
     * @see #validateFromInput()
     */
    public Route validateFromInput(long id) {
        String name = validateSetName();
        Coordinates coordinates = new Coordinates(validateSetCoordinatesX(), validateSetCoordinatesY());
        Location locationFrom = new Location(validateSetLocationX(), validateSetLocationY(), validateSetLocationZ());
        Location locationTo = new Location(validateSetLocationX(), validateSetLocationY(), validateSetLocationZ());
        int distance = validateSetDistance();
        return new Route(id, name, coordinates, locationFrom, locationTo, distance);
    }

    /**
     * Валидирует и возвращает название маршрута.
     * <p>
     * Запрашивает ввод названия у пользователя и проверяет, что оно не пустое.
     * В случае пустого ввода цикл повторяется до получения непустой строки.
     *
     * @return валидное название маршрута (непустая строка)
     */
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

    /**
     * Валидирует и возвращает Y координату для {@link Coordinates}.
     * <p>
     * Запрашивает ввод и проверяет следующие условия:
     * <ul>
     *   <li>Значение не может быть пустым</li>
     *   <li>Должно быть целым числом типа long</li>
     *   <li>Должно быть в диапазоне (0, 20]</li>
     * </ul>
     * При несоответствии любому условию выводится соответствующее сообщение
     * и ввод повторяется.
     *
     * @return валидное значение Y координаты
     */
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

    /**
     * Валидирует и возвращает X координату для {@link Coordinates}.
     * <p>
     * Запрашивает ввод и проверяет следующие условия:
     * <ul>
     *   <li>Значение не может быть пустым</li>
     *   <li>Должно быть целым числом типа long</li>
     *   <li>Должно быть в диапазоне (0, 108]</li>
     * </ul>
     * При несоответствии любому условию выводится соответствующее сообщение
     * и ввод повторяется.
     *
     * @return валидное значение X координаты
     */
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

    /**
     * Валидирует и возвращает X координату для {@link Location}.
     * <p>
     * Запрашивает ввод и проверяет следующие условия:
     * <ul>
     *   <li>Значение не может быть пустым</li>
     *   <li>Должно быть числом (может быть int или float)</li>
     * </ul>
     * Метод поддерживает ввод как целых чисел, так и чисел с плавающей точкой.
     * В случае ошибки ввода выводится сообщение и ввод повторяется.
     *
     * @return валидное значение X координаты локации
     */
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

    /**
     * Валидирует и возвращает Y координату для {@link Location}.
     * <p>
     * Запрашивает ввод и проверяет следующие условия:
     * <ul>
     *   <li>Значение не может быть пустым</li>
     *   <li>Должно быть числом с плавающей точкой (может быть преобразовано в Double)</li>
     * </ul>
     * В случае ошибки ввода выводится сообщение и ввод повторяется.
     *
     * @return валидное значение Y координаты локации (не может быть null)
     */
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

    /**
     * Валидирует и возвращает Z координату для {@link Location}.
     * <p>
     * Запрашивает ввод и проверяет следующие условия:
     * <ul>
     *   <li>Значение не может быть пустым</li>
     *   <li>Должно быть целым числом типа int</li>
     * </ul>
     * В случае ошибки ввода выводится сообщение и ввод повторяется.
     *
     * @return валидное значение Z координаты локации
     */
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

    /**
     * Валидирует и возвращает значение расстояния (distance).
     * <p>
     * Запрашивает ввод и проверяет следующие условия:
     * <ul>
     *   <li>Ввод должен быть целым числом (проверяется через {@link ManagerInputOutput#hasNextIntIO()})</li>
     *   <li>Значение должно быть больше 1</li>
     * </ul>
     * В режиме скрипта используется специальная логика чтения через {@link ManagerInputOutput#nextIntIO()}.
     * При несоответствии условиям выводится сообщение и ввод повторяется.
     *
     * @return валидное значение расстояния (больше 1)
     */
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