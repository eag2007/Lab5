package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.example.classes.runner.Runner.managerInputOutput;
import static org.example.classes.runner.Runner.managerParserCommand;

public class ExecuteScript implements Command {
    private static final Set<String> setPaths = new HashSet<>();

    public boolean checkArg(String[] args) {
        return args.length == 1;
    }

    public void executeCommand(String[] args) {
        if (!checkArg(args)) {
            managerInputOutput.writeLineIO("Ошибка, синтаксис команды: execute_script file_name\n");
            return;
        }

        String fileName = args[0];

        File file = new File("src/main/java/org/example/" + fileName);
        if (!file.exists()) {
            file = new File(fileName);
        }

        if (!file.exists()) {
            managerInputOutput.writeLineIO("Ошибка: файл '" + fileName + "' не найден\n");
            return;
        }

        String pathFile = file.getAbsolutePath();

        if (setPaths.contains(pathFile)) {
            managerInputOutput.writeLineIO("Ошибка: рекурсия в скрипте " + fileName + "\n");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            setPaths.add(pathFile);
            managerInputOutput.setFileExecute(reader);

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) continue;

                managerInputOutput.writeLineIO("Строка " + lineNumber + ": " + line + "\n");
                managerParserCommand.parserCommand(line);
            }

            managerInputOutput.writeLineIO("Скрипт выполнен\n", Colors.GREEN);

        } catch (IOException e) {
            managerInputOutput.writeLineIO("Ошибка: " + e.getMessage() + "\n");
        } finally {
            setPaths.remove(pathFile);
            managerInputOutput.setConsoleExecute();
        }
    }

    @Override
    public String toString() {
        return "execute_script file_name - выполняет скрипт из файла";
    }
}