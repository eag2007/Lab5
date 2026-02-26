package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.*;

/**
 * Команда {@code save} сохраняет коллекцию в файл формата CSV.
 * <p>
 * Если указано имя файла, сохраняет в него. Если имя файла не указано,
 * сохраняет в файл, путь к которому хранится в переменной окружения JAVAMETASPACE.
 * </p>
 *
 * @author
 * @version 1.0
 * @see Command
 * @see org.example.classes.managers.ManagerReadWrite#writeCSV(String)
 */
public class Save implements Command {

    /**
     * Выполняет команду сохранения коллекции в файл.
     *
     * @param args аргументы команды, где args[0] (опционально) — имя файла для сохранения
     */
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            if (args.length == 1) {
                managerReadWrite.writeCSV(args[0]);
            } else {
                managerReadWrite.writeCSV(javaMetaspace);
            }
        } else {
            managerInputOutput.writeLineIO("Неправильное количество аргументов или неправильный тип\n", Colors.RED);
        }
    }

    /**
     * Проверяет, что количество аргументов не превышает 1.
     *
     * @param args массив аргументов для проверки
     * @return {@code true}, если аргументов 0 или 1, иначе {@code false}
     */
    public boolean checkArg(String[] args) {
        return args.length <= 1;
    }

    /**
     * Возвращает строковое представление команды для справки.
     *
     * @return описание команды
     */
    @Override
    public String toString() {
        return "save file_name - сохраняет коллекцию в файл";
    }
}