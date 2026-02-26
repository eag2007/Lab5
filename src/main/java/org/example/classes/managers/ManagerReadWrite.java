package org.example.classes.managers;

import org.example.enums.Colors;
import org.example.classes.Route;
import org.example.interfaces.ReadWrite;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

/**
 * Менеджер для чтения и записи коллекции в CSV-файлы (Singleton).
 * <p>
 * Обеспечивает импорт коллекции из файла при запуске и экспорт при выполнении команды {@code save}.
 * </p>
 *
 * @author
 * @version 1.0
 * @see ReadWrite
 */
public class ManagerReadWrite implements ReadWrite {
    /** Единственный экземпляр класса (Singleton). */
    private static ManagerReadWrite managerReadWrite;

    /** Приватный конструктор для Singleton. */
    private ManagerReadWrite() {
    }

    /**
     * Возвращает единственный экземпляр менеджера.
     *
     * @return экземпляр {@code ManagerReadWrite}
     */
    public static ManagerReadWrite getInstance() {
        if (managerReadWrite == null) {
            managerReadWrite = new ManagerReadWrite();
        }
        return managerReadWrite;
    }

    /**
     * Читает данные из CSV-файла.
     * <p>
     * Файл должен быть в кодировке UTF-8. Первая строка считается заголовком и пропускается.
     * </p>
     *
     * @param pathToFile путь к файлу
     * @return список строк, где каждая строка — массив полей, разделенных ';'
     */
    public List<String[]> readCSV(String pathToFile) {
        if (pathToFile == null) {
            return new ArrayList<>();
        }
        List<String[]> data = new ArrayList<>();

        try (BufferedReader readCSVFile = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            line = readCSVFile.readLine(); // Чтение заголовка
            while (line != null) {
                if (!line.trim().isEmpty()) {
                    data.add(line.split(";"));
                }
                line = readCSVFile.readLine();
            }
            managerInputOutput.writeLineIO("Загружено строк: " + (data.size() - 1) + "\n", Colors.GREEN);
        } catch (IOException e) {
            managerInputOutput.writeLineIO("Ошибка чтение файла коллекция пуста: " + e.getMessage() + "\n");
        }

        return data;
    }

    /**
     * Записывает текущую коллекцию в CSV-файл.
     * <p>
     * Запись производится в кодировке UTF-8. Если файл существует, он будет перезаписан.
     * </p>
     *
     * @param pathToFile путь к файлу для сохранения
     * @return {@code true}, если запись прошла успешно, иначе {@code false}
     */
    public boolean writeCSV(String pathToFile) {
        PriorityQueue<Route> routes = managerCollections.getCollectionsRoute();

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(pathToFile), StandardCharsets.UTF_8)) {

            writer.write("id;name;coordinates_x;coordinates_y;creation_date;from_x;from_y;from_z;to_x;to_y;to_z;distance");
            writer.write("\n");

            for (Route route : routes) {
                StringBuilder line = new StringBuilder();
                line.append(route.getId()).append(";");
                line.append(route.getName()).append(";");
                line.append(route.getCoordinates().getX()).append(";");
                line.append(route.getCoordinates().getY()).append(";");

                ZonedDateTime creationDate = route.getCreationDate();
                line.append(creationDate).append(";");

                line.append(route.getFrom().getX()).append(";");
                line.append(route.getFrom().getY()).append(";");
                line.append(route.getFrom().getZ()).append(";");

                line.append(route.getTo().getX()).append(";");
                line.append(route.getTo().getY()).append(";");
                line.append(route.getTo().getZ()).append(";");

                line.append(route.getDistance());

                writer.write(line.toString());
                writer.write("\n");
            }

            writer.flush();
            managerInputOutput.writeLineIO("Успешно записано " + routes.size() + " маршрутов\n", Colors.GREEN);
            return true;

        } catch (IOException e) {
            managerInputOutput.writeLineIO("Ошибка при записи: " + e.getMessage() + "\n");
            return false;
        }
    }
}