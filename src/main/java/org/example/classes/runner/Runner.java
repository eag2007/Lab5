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
//        System.out.println("JAVAENV" + javaEnv);
//
//        // /home/evgeniy/IdeaProjects/Lab5-Graddle/src/main/java/org/example/csv/collections.csv
//
        managerInputOutput.writeLineIO("=====================================\n");

        // List<String[]> data = managerReadWrite.readCSV("/home/evgeniy/" +
        //        "IdeaProjects/Lab5-Graddle/src/main/java/org/example/csv/collections.csv");

        List<String[]> data = managerReadWrite.readCSV(this.javaEnv);

        managerInputOutput.writeLineIO("=====================================\n");

        managerCollections.addAllCollection(data);

//        for (String[] row : data) {
//            String line = String.format("%-3s | %-20s | %-3s | %-3s | %-6s | %-6s | %-4s | %-6s | %-6s | %-4s | %-5s | %-25s",
//                    row[0], row[1], row[2], row[3], row[4], row[5], row[6],
//                    row[7], row[8], row[9], row[10], row[11]);
//
//            managerInputOutput.writeLineIO(line + "\n");
//        }
//
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