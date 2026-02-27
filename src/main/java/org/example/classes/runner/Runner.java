package org.example.classes.runner;

import org.example.classes.managers.*;
import org.example.enums.Colors;

import java.util.List;
import java.util.NoSuchElementException;

public class Runner {
    public static ManagerParserCommand managerParserCommand;
    public static final ManagerInputOutput managerInputOutput = ManagerInputOutput.getInstance();
    public static ManagerCollections managerCollections;
    public static final ManagerReadWrite managerReadWrite = ManagerReadWrite.getInstance();
    public static ManagerValidationData managerValidationData;
    public static String javaMetaspace = System.getenv("JAVAMETASPACE");

    public Runner() {
        managerParserCommand = new ManagerParserCommand();
        managerCollections = new ManagerCollections();
        managerValidationData = new ManagerValidationData();

        managerInputOutput.writeLineIO("=====================================\n");

        List<String[]> data = managerReadWrite.readCSV(javaMetaspace);

        managerInputOutput.writeLineIO("=====================================\n");

        managerCollections.addAllCollection(data);
    }

    public void run() {
        try {
            while (true) {
                managerInputOutput.writeLineIO("Введите команду : ", Colors.BLUE);
                String command = managerInputOutput.readLineIO();

                boolean flag = managerParserCommand.parserCommand(command);
                if (!flag) {
                    managerInputOutput.writeLineIO("Неизвестная комманда\n");
                }
            }
        } catch (NoSuchElementException e) {
            managerInputOutput.writeLineIO("Завершение ввода\n", Colors.GREEN);
        }
    }
}