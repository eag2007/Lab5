package org.example.classes.managers;

import org.example.Colors;
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
        List<String[]> data = new ArrayList<>();

        try (BufferedReader readCSVFile = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            line = readCSVFile.readLine();
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
