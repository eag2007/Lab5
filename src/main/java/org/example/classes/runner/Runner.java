package org.example.classes.runner;

import org.example.classes.managers.*;

import java.util.List;

public class Runner {
    public static ManagerParserCommand managerParserCommand;
    public static ManagerInputOutput managerInputOutput;
    public static ManagerCollections managerCollections;
    public static ManagerReadWrite managerReadWrite;
    public static ManagerValidationData managerValidationData;

    public static String javaMetaspace = System.getenv("JAVAMETASPACE");

    public Runner() {
        managerParserCommand = new ManagerParserCommand();
        managerInputOutput = ManagerInputOutput.getInstance();
        managerReadWrite = ManagerReadWrite.getInstance();
        managerCollections = new ManagerCollections();
        managerValidationData = new ManagerValidationData();

        managerInputOutput.writeLineIO("=====================================\n");

        List<String[]> data = managerReadWrite.readCSV(this.javaMetaspace);

        managerInputOutput.writeLineIO("=====================================\n");

        managerCollections.addAllCollection(data);

    }

    public void run() {

        while (true) {
            managerInputOutput.writeLineIO("Введите команду : ");
            String command = managerInputOutput.readLineIO();

            boolean flag = managerParserCommand.parserCommand(command);
            if (!flag) {
                managerInputOutput.writeLineIO("Неизвестная комманда\n");
            }
        }
    }
}