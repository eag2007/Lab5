package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

/**
 * Команда {@code info} выводит информацию о коллекции.
 * <p>
 * Отображает: тип коллекции, количество элементов и время ее инициализации.
 * </p>
 *
 * @author
 * @version 1.0
 * @see Command
 */
public class Info implements Command {

    /**
     * Выполняет команду вывода информации о коллекции.
     *
     * @param args аргументы команды (ожидается отсутствие аргументов)
     */
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            managerInputOutput.writeLineIO("Количество элементов: " +
                    managerCollections.getSizeCollections() + "\n");
            managerInputOutput.writeLineIO("Время инициализации: " + managerCollections.getTimeInit() + "\n");
            managerInputOutput.writeLineIO("Тип данных: Route\n");
        } else {
            managerInputOutput.writeLineIO("Неверное количество аргументов\n", Colors.RED);
        }
    }

    /**
     * Проверяет, что команда вызвана без аргументов.
     *
     * @param args массив аргументов для проверки
     * @return {@code true}, если аргументов нет, иначе {@code false}
     */
    public boolean checkArg(String[] args) {
        return args.length == 0;
    }

    /**
     * Возвращает строковое представление команды для справки.
     *
     * @return описание команды
     */
    @Override
    public String toString() {
        return "info - выводит в стандартный поток вывода информацию о коллекции";
    }
}