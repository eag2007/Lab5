package org.example.classes.runner;

import org.example.classes.managers.*;
import org.example.enums.Colors;

import java.util.List;

/**
 * Главный класс-запускатор приложения.
 * <p>
 * Инициализирует все менеджеры, загружает коллекцию из файла, путь к которому указан
 * в переменной окружения {@code JAVAMETASPACE}, и запускает основной цикл обработки команд.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class Runner {
    /** Парсер команд. */
    public static ManagerParserCommand managerParserCommand;
    /** Менеджер ввода-вывода. */
    public static ManagerInputOutput managerInputOutput;
    /** Менеджер коллекции. */
    public static ManagerCollections managerCollections;
    /** Менеджер чтения/записи. */
    public static ManagerReadWrite managerReadWrite;
    /** Менеджер валидации данных. */
    public static ManagerValidationData managerValidationData;

    /** Путь к файлу для автосохранения/загрузки из переменной окружения. */
    public static String javaMetaspace = System.getenv("JAVAMETASPACE");

    /**
     * Конструктор, выполняющий инициализацию.
     * <p>
     * Создает экземпляры всех менеджеров (используя паттерн Singleton для некоторых)
     * и загружает коллекцию из CSV-файла, путь к которому получен из переменной окружения.
     * </p>
     */
    public Runner() {
        managerParserCommand = new ManagerParserCommand();
        managerInputOutput = ManagerInputOutput.getInstance();
        managerReadWrite = ManagerReadWrite.getInstance();
        managerCollections = new ManagerCollections();
        managerValidationData = new ManagerValidationData();

        managerInputOutput.writeLineIO("=====================================\n");

        List<String[]> data = managerReadWrite.readCSV(javaMetaspace);

        managerInputOutput.writeLineIO("=====================================\n");

        managerCollections.addAllCollection(data);
    }

    /**
     * Запускает основной цикл обработки команд.
     * <p>
     * В бесконечном цикле запрашивает команду у пользователя и передает её на обработку
     * парсеру команд.
     * </p>
     */
    public void run() {
        while (true) {
            managerInputOutput.writeLineIO("Введите команду : ", Colors.BLUE);
            String command = managerInputOutput.readLineIO();

            boolean flag = managerParserCommand.parserCommand(command);
            if (!flag) {
                managerInputOutput.writeLineIO("Неизвестная комманда\n");
            }
        }
    }
}