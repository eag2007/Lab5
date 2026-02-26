package org.example.classes.commands;

import org.example.classes.Route;
import org.example.enums.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

/**
 * Команда {@code remove_by_id} удаляет элемент из коллекции по его уникальному идентификатору.
 *
 * @author
 * @version 1.0
 * @see Command
 * @see Route#getId()
 */
public class RemoveById implements Command {

    /**
     * Выполняет команду удаления элемента по ID.
     *
     * @param args аргументы команды, где args[0] — идентификатор элемента
     */
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            Long id = Long.parseLong(args[0]);
            managerCollections.removeByIdCollections(id);
        }
    }

    /**
     * Проверяет аргументы команды.
     * <p>
     * Убеждается, что передан ровно один аргумент, и его можно корректно преобразовать в целое число.
     * </p>
     *
     * @param args массив аргументов для проверки
     * @return {@code true}, если аргумент корректен, иначе {@code false}
     */
    public boolean checkArg(String[] args) {
        if (args.length == 1) {
            try {
                Integer.parseInt(args[0]);
                return true;
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Ошибка: аргумент должен быть целым числом в диапазоне от " +
                        Long.MIN_VALUE + " до " + Long.MAX_VALUE + "\n", Colors.RED);
                return false;
            }
        }
        return false;
    }

    /**
     * Возвращает строковое представление команды для справки.
     *
     * @return описание команды
     */
    @Override
    public String toString() {
        return "remove_by_id id - удалить элемент из коллекции по его id";
    }
}