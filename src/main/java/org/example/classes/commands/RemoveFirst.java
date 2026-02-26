package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

/**
 * Команда {@code remove_first} удаляет первый элемент из коллекции.
 * <p>
 * Первый элемент определяется естественным порядком очереди с приоритетом (PriorityQueue).
 * </p>
 *
 * @author
 * @version 1.0
 * @see Command
 */
public class RemoveFirst implements Command {

    /**
     * Выполняет команду удаления первого элемента.
     *
     * @param args аргументы команды (ожидается отсутствие аргументов)
     */
    public void executeCommand(String[] args) {
        if (checkArg(args)){
            managerCollections.removeFirstCollections();
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
        return "remove_first  - удаляет первый элемент из коллекции";
    }
}