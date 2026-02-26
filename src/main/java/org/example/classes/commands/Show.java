package org.example.classes.commands;

import org.example.classes.Route;
import org.example.enums.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

/**
 * Команда {@code show} выводит все элементы коллекции в строковом представлении.
 * <p>
 * Элементы выводятся в отсортированном порядке (по {@link Route#compareTo(Route)}).
 * </p>
 *
 * @author
 * @version 1.0
 * @see Command
 * @see Route
 */
public class Show implements Command {

    /**
     * Выполняет команду вывода всех элементов коллекции.
     *
     * @param args аргументы команды (ожидается отсутствие аргументов)
     */
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            if (managerCollections.getCollectionsRoute().isEmpty()) {
                managerInputOutput.writeLineIO("Коллекция пуста\n", Colors.YELLOW);
            }
            for (Route route : managerCollections.getSortedCollections()) {
                String line = String.format("%-3s | %-20s | %-3s | %-3s | %-6s | %-6s | %-4s | %-6s | %-6s | %-4s | %-5s | %-25s",
                        route.getId(), route.getName(), route.getCoordinates().getX(), route.getCoordinates().getY(),
                        route.getCreationDate(), route.getFrom().getX(), route.getFrom().getY(), route.getFrom().getZ(),
                        route.getTo().getX(), route.getTo().getY(), route.getTo().getZ(), route.getDistance());
                managerInputOutput.writeLineIO(line + "\n");
            }
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
        return "show - выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}