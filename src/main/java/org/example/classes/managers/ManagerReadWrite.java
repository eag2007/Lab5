package org.example.classes.managers;

import org.example.interfaces.ReadWrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            managerInputOutput.writeLineIO("Загружено строк: " + data.size() + "\n");
        } catch (IOException e) {
            managerInputOutput.writeLineIO("Ошибка чтение файла коллекция пуста: " + e.getMessage() + "\n");
        }

        return data;
    }
}
