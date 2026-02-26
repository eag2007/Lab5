package org.example.classes.commands;

import org.example.classes.Route;
import org.example.enums.Colors;
import org.example.interfaces.Command;

import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

/**
 * Команда {@code filter_less_than_distance} выводит элементы,
 * значение поля {@code distance} которых меньше заданного.
 *
 * @author
 * @version 1.0
 * @see Command
 * @see Route#getDistance()
 */
public class FilterLessThanDistance implements Command {

    /**
     * Выполняет команду фильтрации по расстоянию.
     * <p>
     * Принимает целое число (distance) в качестве аргумента.
     * Проходит по коллекции и выводит все элементы, у которых {@code distance < заданного}.
     * </p>
     *
     * @param args аргументы команды, где args[0] — значение для сравнения
     */
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            Integer distance = Integer.parseInt(args[0]);
            PriorityQueue<Route> routes = managerCollections.getCollectionsRoute();
            for (Route route : routes) {
                if (route.getDistance() < distance) {
                    String line = String.format("%-3s | %-20s | %-3s | %-3s | %-6s | %-6s | %-4s | %-6s | %-6s | %-4s | %-5s | %-25s",
                            route.getId(), route.getName(), route.getCoordinates().getX(), route.getCoordinates().getY(),
                            route.getCreationDate(), route.getFrom().getX(), route.getFrom().getY(), route.getFrom().getZ(),
                            route.getTo().getX(), route.getTo().getY(), route.getTo().getZ(), route.getDistance());
                    managerInputOutput.writeLineIO(line + "\n");
                }
            }
        } else {
            managerInputOutput.writeLineIO("Неправильное количество аргументов или их тип\n", Colors.RED);
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
                        Integer.MIN_VALUE + " до " + Integer.MAX_VALUE + "\n", Colors.RED);
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
        return "filter_less_than_distance distance - выводит элементы значения поля distance которых меньше заданного";
    }
}