package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.classes.Route;
import org.example.interfaces.Command;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.*;

/**
 * Команда {@code add_if_max} добавляет новый элемент в коллекцию,
 * если его значение превышает значение наибольшего элемента в коллекции.
 * <p>
 * Сравнение элементов происходит на основе их естественного порядка,
 * определенного в методе {@link Route#compareTo(Route)}.
 * </p>
 *
 * @author
 * @version 1.0
 * @see Command
 * @see Route#compareTo(Route)
 */
public class AddIfMax implements Command {

    /**
     * Выполняет команду добавления элемента, если он максимальный.
     * <p>
     * Валидирует и создает новый объект {@code Route}. Если коллекция не пуста,
     * находит максимальный элемент и сравнивает с ним новый. Если новый элемент больше,
     * он добавляется в коллекцию.
     * </p>
     *
     * @param args аргументы команды (ожидается отсутствие аргументов или аргумент "Route")
     */
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            Route newRoute;

            if (managerInputOutput.isScriptMode()) {
                newRoute = managerValidationData.validateFromFile();
            } else {
                newRoute = managerValidationData.validateFromInput();
            }

            PriorityQueue<Route> routes = managerCollections.getCollectionsRoute();

            if (newRoute == null) {
                managerInputOutput.writeLineIO("Объект не создан (неправильно введены поля)\n", Colors.RED);
                return;
            }

            if (!routes.isEmpty()) {
                Route maxRoute = routes.stream()
                        .max(Comparator.naturalOrder())
                        .orElse(null);

                if (maxRoute != null && newRoute.compareTo(maxRoute) > 0) {
                    managerCollections.addCollections(newRoute);
                    managerInputOutput.writeLineIO("Элемент добавлен в коллекцию (превышает максимальный)\n", Colors.GREEN);
                } else {
                    managerInputOutput.writeLineIO("Элемент не добавлен (не превышает максимальный)\n");
                }
            } else {
                managerCollections.addCollections(newRoute);
                managerInputOutput.writeLineIO("Коллекция была пуста, элемент добавлен\n");
            }
        } else {
            managerInputOutput.writeLineIO("Неправильное количество аргументов\n", Colors.RED);
        }
    }

    /**
     * Проверяет аргументы команды.
     *
     * @param args массив аргументов для проверки
     * @return {@code true}, если аргументы корректны (пусто или "Route"), иначе {@code false}
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
        return "add_if_max {element} - добавляет новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента в коллекции";
    }
}