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

public class ManagerReadWrite implements ReadWrite {
    private static ManagerReadWrite managerReadWrite;

    private ManagerReadWrite() {
    }

    public static ManagerReadWrite getInstance() {
        if (managerReadWrite == null) {
            managerReadWrite = new ManagerReadWrite();
        }
        return managerReadWrite;
    }

    public List<String[]> readCSV(String pathToFile) {
        if (pathToFile == null) {
            return new ArrayList<>();
        }

        List<String[]> data = new ArrayList<>();
        File file = new File(pathToFile);

        if (!file.exists()) {
            managerInputOutput.writeLineIO("Файл не найден: " + pathToFile + "\n", Colors.RED);
            return data;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            int lineNumber = 1;
            ManagerValidationData validator = new ManagerValidationData();

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;

                String[] fields = line.split(";");

                if (!validator.validateCSVFields(fields, lineNumber)) {
                    managerInputOutput.writeLineIO("Ошибка в строке " + lineNumber + ". Файл не загружен\n", Colors.RED);
                    return new ArrayList<>();
                }

                data.add(fields);
            }

            managerInputOutput.writeLineIO("Загружено строк: " + data.size() + "\n", Colors.GREEN);

        } catch (IOException e) {
            managerInputOutput.writeLineIO("Ошибка чтения: " + e.getMessage() + "\n", Colors.RED);
        }

        return data;
    }

    public boolean writeCSV(String pathToFile) {
        if (pathToFile == null || pathToFile.trim().isEmpty()) {
            managerInputOutput.writeLineIO("Ошибка: путь не указан\n", Colors.RED);
            return false;
        }

        File file = new File(pathToFile);

        if (!pathToFile.toLowerCase().endsWith(".csv")) {
            managerInputOutput.writeLineIO("Ошибка: файл должен быть с расширением .csv\n", Colors.RED);
            return false;
        }

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            managerInputOutput.writeLineIO("Ошибка: директория " + parentDir + " не существует\n", Colors.RED);
            return false;
        }

        PriorityQueue<Route> routes = managerCollections.getCollectionsRoute();

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8)) {

            writer.write("id;name;coordinates_x;coordinates_y;creation_date;from_x;from_y;from_z;to_x;to_y;to_z;distance;price\n");

            for (Route route : routes) {
                String line = route.getId() + ";" +
                        route.getName() + ";" +
                        route.getCoordinates().getX() + ";" +
                        route.getCoordinates().getY() + ";" +
                        route.getCreationDate() + ";" +
                        route.getFrom().getX() + ";" +
                        route.getFrom().getY() + ";" +
                        route.getFrom().getZ() + ";" +
                        route.getTo().getX() + ";" +
                        route.getTo().getY() + ";" +
                        route.getTo().getZ() + ";" +
                        route.getDistance() + ";" +
                        route.getPrice() + "\n";
                writer.write(line);
            }

            writer.flush();
            managerInputOutput.writeLineIO("Сохранено " + routes.size() + " маршрутов в " + pathToFile + "\n", Colors.GREEN);
            return true;

        } catch (FileNotFoundException e) {
            managerInputOutput.writeLineIO("Ошибка: невозможно создать файл\n", Colors.RED);
            return false;
        } catch (IOException e) {
            managerInputOutput.writeLineIO("Ошибка записи: " + e.getMessage() + "\n", Colors.RED);
            return false;
        }
    }
}