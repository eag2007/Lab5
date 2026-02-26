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

/**
 * Команда {@code execute_script} выполняет скрипт из указанного файла.
 * <p>
 * Считывает команды из файла построчно и передает их на выполнение парсеру.
 * Содержит защиту от рекурсивных вызовов (повторного выполнения одного и того же файла).
 * </p>
 *
 * @author
 * @version 1.0
 * @see Command
 */
public class ExecuteScript implements Command {
    /**
     * Множество абсолютных путей выполняемых скриптов для обнаружения рекурсии.
     */
    private static final Set<String> setPaths = new HashSet<>();

    /**
     * Выполняет команду чтения и выполнения скрипта.
     * <p>
     * Ищет файл сначала в директории 'src/main/java/org/example/', затем в корне проекта.
     * Устанавливает режим скрипта для {@code ManagerInputOutput} и построчно парсит команды.
     * После завершения (или ошибки) восстанавливает консольный режим ввода.
     * </p>
     *
     * @param args аргументы команды, где args[0] — имя файла скрипта
     */
    public void executeCommand(String[] args) {
        if (!checkArg(args)) {
            managerInputOutput.writeLineIO("Ошибка, синтаксис команды: execute_script file_name\n", Colors.RED);
            return;
        }

        String fileName = args[0];
        File file = new File("src/main/java/org/example/" + fileName);
        if (!file.exists()) {
            file = new File(fileName);
        }

        if (!file.exists()) {
            managerInputOutput.writeLineIO("Ошибка: файл '" + fileName + "' не найден\n", Colors.RED);
            return;
        }

        String pathFile = file.getAbsolutePath();

        if (setPaths.contains(pathFile)) {
            managerInputOutput.writeLineIO("Ошибка: рекурсия в скрипте " + fileName + "\n", Colors.RED);
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
            managerInputOutput.writeLineIO("Ошибка: " + e.getMessage() + "\n", Colors.RED);
        } finally {
            setPaths.remove(pathFile);
            managerInputOutput.setConsoleExecute();
        }
    }

    /**
     * Проверяет, что передан ровно один аргумент (имя файла).
     *
     * @param args массив аргументов для проверки
     * @return {@code true}, если длина массива равна 1, иначе {@code false}
     */
    public boolean checkArg(String[] args) {
        return args.length == 1;
    }

    /**
     * Возвращает строковое представление команды для справки.
     *
     * @return описание команды
     */
    @Override
    public String toString() {
        return "execute_script file_name - выполняет скрипт из файла";
    }
}