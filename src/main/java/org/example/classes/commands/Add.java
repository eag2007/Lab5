package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.classes.Route;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.*;

/**
 * Команда {@code add} добавляет новый элемент в коллекцию.
 * <p>
 * В зависимости от режима работы (консольный или скриптовый) запрашивает ввод данных
 * у пользователя или читает их из файла. После успешного создания объекта {@link Route}
 * добавляет его в коллекцию.
 * </p>
 *
 * @author
 * @version 1.0
 * @see Command
 * @see Route
 */
public class Add implements Command {

    /**
     * Выполняет команду добавления элемента.
     * <p>
     * Сначала проверяется корректность аргументов команды. Затем, в зависимости от режима,
     * вызывается соответствующий метод валидации для создания объекта {@code Route}.
     * В случае успеха объект добавляется в коллекцию.
     * </p>
     *
     * @param args аргументы команды (ожидается отсутствие аргументов или аргумент "Route")
     */
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            if (!managerInputOutput.isScriptMode()) {
                managerCollections.addCollections(managerValidationData.validateFromInput());
                managerInputOutput.writeLineIO("Элемент создан\n", Colors.GREEN);
            } else {
                Route element = managerValidationData.validateFromFile();
                if (element != null) {
                    managerCollections.addCollections(element);
                    managerInputOutput.writeLineIO("Элемент создан\n", Colors.GREEN);
                } else {
                    managerInputOutput.writeLineIO("Ошибка: невозможно создать элемент\n");
                    managerInputOutput.writeLineIO("Объект не создан\n", Colors.RED);
                }
            }
        } else {
            managerInputOutput.writeLineIO("Неправильное количество элементов\n", Colors.RED);
        }
    }

    /**
     * Проверяет аргументы команды.
     * <p>
     * Команда считается корректной, если массив аргументов пуст или содержит один элемент "Route".
     * </p>
     *
     * @param args массив аргументов для проверки
     * @return {@code true}, если аргументы корректны, иначе {@code false}
     */
    public boolean checkArg(String[] args) {
        if (args.length == 0) {
            return true;
        }
        if (args.length == 1 && args[0].equals("Route")) {
            return true;
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
        return "add - добавляет новый элемент в коллекцию";
    }
}