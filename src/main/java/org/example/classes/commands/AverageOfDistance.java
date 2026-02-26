package org.example.classes.commands;

import org.example.classes.Route;
import org.example.enums.Colors;
import org.example.interfaces.Command;

import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

/**
 * Команда {@code average_of_distance} выводит среднее значение поля {@code distance}
 * для всех элементов коллекции.
 * <p>
 * Если коллекция пуста, среднее значение считается равным 0.
 * </p>
 *
 * @author
 * @version 1.0
 * @see Command
 * @see Route#getDistance()
 */
public class AverageOfDistance implements Command {

    /**
     * Выполняет команду вычисления и вывода среднего расстояния.
     * <p>
     * Проходит по всем элементам коллекции, суммирует их расстояние и делит на размер коллекции.
     * </p>
     *
     * @param args аргументы команды (ожидается отсутствие аргументов)
     */
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            PriorityQueue<Route> collectionsRoute = managerCollections.getCollectionsRoute();
            Double distance = 0d;

            for (Route element : collectionsRoute) {
                distance += element.getDistance();
            }
            int sizeCollections = managerCollections.getSizeCollections();
            if (sizeCollections != 0) {
                distance = distance / managerCollections.getSizeCollections();
            }
            managerInputOutput.writeLineIO("Среднее значение distance всех элементов : " + distance + "\n");
        } else {
            managerInputOutput.writeLineIO("Неправильное количество аргументов\n", Colors.RED);
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
        return "average_of_distance - выводит среднее значение поля distance для всех элементов коллекции";
    }
}