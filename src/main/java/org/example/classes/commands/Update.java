package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

/**
 * Команда {@code update} обновляет значение элемента коллекции, ID которого равен заданному.
 * <p>
 * Команда удаляет старый элемент с указанным ID и добавляет новый, созданный на основе введенных пользователем данных.
 * </p>
 *
 * @author
 * @version 1.0
 * @see Command
 */
public class Update implements Command {

    /**
     * Выполняет команду обновления элемента по ID.
     *
     * @param args аргументы команды, где args[0] — идентификатор обновляемого элемента
     */
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            managerCollections.updateCollections(Long.parseLong(args[0]));
            managerInputOutput.writeLineIO("Элемент обновлён", Colors.GREEN);
        } else {
            managerInputOutput.writeLineIO("Неправильный порядок аргументов или их тип\n", Colors.RED);
        }
    }

    /**
     * Проверяет аргументы команды.
     * <p>
     * Допустимые форматы: <br>
     * 1. {@code update id} — ровно один аргумент, который является целым числом. <br>
     * 2. {@code update id Route} — два аргумента, первый — целое число, второй — строка "Route".
     * </p>
     *
     * @param args массив аргументов для проверки
     * @return {@code true}, если аргументы корректны, иначе {@code false}
     */
    public boolean checkArg(String[] args) {
        if (args.length == 2 && args[1].equals("Route")) {
            try {
                Long.parseLong(args[0]);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else if (args.length == 1) {
            try {
                Long.parseLong(args[0]);
                return true;
            } catch (NumberFormatException e) {
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
        return "update id {element} - обновляет значение элемента коллекции, id которого равен заданному";
    }
}