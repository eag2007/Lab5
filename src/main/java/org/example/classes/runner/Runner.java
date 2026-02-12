package org.example.classes.runner;

import org.example.classes.managers.ManagerCollections;
import org.example.classes.managers.ManagerInputOutput;
import org.example.classes.managers.ManagerParserCommand;

public class Runner {
    public static ManagerParserCommand managerParserCommand;
    public static ManagerInputOutput managerInputOutput;
    public static ManagerCollections managerCollections;

    public Runner() {
        managerParserCommand = new ManagerParserCommand();
        managerInputOutput = ManagerInputOutput.getInstance();
        managerCollections = new ManagerCollections();
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