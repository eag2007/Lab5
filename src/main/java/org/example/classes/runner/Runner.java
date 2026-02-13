package org.example.classes.runner;

import org.example.classes.managers.ManagerCollections;
import org.example.classes.managers.ManagerInputOutput;
import org.example.classes.managers.ManagerParserCommand;
import org.example.classes.managers.ManagerReadWrite;

import java.util.List;

public class Runner {
    public static ManagerParserCommand managerParserCommand;
    public static ManagerInputOutput managerInputOutput;
    public static ManagerCollections managerCollections;
    public static ManagerReadWrite managerReadWrite;

    private String javaEnv;

    public Runner() {
        managerParserCommand = new ManagerParserCommand();
        managerInputOutput = ManagerInputOutput.getInstance();
        managerReadWrite = ManagerReadWrite.getInstance();
        managerCollections = new ManagerCollections();

        this.javaEnv = System.getenv("JAVAENV");

        managerInputOutput.writeLineIO("=====================================\n");

         List<String[]> data = managerReadWrite.readCSV("/home/evgeniy/" +
                "IdeaProjects/Lab5-Graddle/src/main/java/org/example/csv/collections.csv");

        // List<String[]> data = managerReadWrite.readCSV(this.javaEnv);

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