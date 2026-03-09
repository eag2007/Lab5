package org.example.classes.runner;

import org.example.classes.managers.api.ManagerApi;
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
    public static ManagerApi managerApi = ManagerApi.getInstance();

    public Runner() {
        managerParserCommand = new ManagerParserCommand();
        managerCollections = new ManagerCollections();
        managerValidationData = new ManagerValidationData();

        // ПЕРЕДАЧА СПИСКА КОМАНД ДЛЯ АВТОДОПЛНЕНИЯ
        List<String> commandNames = managerParserCommand.getCommandNames();
        managerInputOutput.setCommands(commandNames);

        managerInputOutput.writeLineIO("=====================================\n");

        List<String[]> data = managerReadWrite.readCSV(javaMetaspace);

        managerInputOutput.writeLineIO("=====================================\n");

        managerCollections.addAllCollection(data);
    }

    public void run() {
        try {
            while (true) {
                String command = managerInputOutput.readLineIO("\u001B[34mВведите команду : \u001B[0m");

                if (command == null || command.isBlank()) {
                    continue;
                }

                boolean flag = managerParserCommand.parserCommand(command);
                if (!flag) {
                    managerInputOutput.writeLineIO("Неизвестная комманда\n");
                }
            }
        } catch (NoSuchElementException e) {
            managerInputOutput.writeLineIO("Завершение ввода\n", Colors.GREEN);
            managerInputOutput.closeIO();
        } catch (RuntimeException e) {
            managerInputOutput.writeLineIO("Что-то пошло не так...\n", Colors.YELLOW);
        }
    }
}