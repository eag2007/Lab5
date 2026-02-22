package org.example.classes.commands;

import org.example.classes.Route;
import org.example.interfaces.Command;

import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class RemoveAllByDistance implements Command {
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            Integer distance = Integer.parseInt(args[0]);
            PriorityQueue<Route> routes = managerCollections.getCollectionsRoute();
            PriorityQueue<Route> routesNew = new PriorityQueue<>(Route.COMPARATOR_COLLECTIONS);
            for (Route route : routes) {
                if (!route.getDistance().equals(distance)) {
                    routesNew.add(route);
                }
            }

            managerCollections.removeAllByDistanceCollections(routesNew);
            managerInputOutput.writeLineIO("Данные удалены\n");
        } else {
            managerInputOutput.writeLineIO("Неправильно количество аргументов и их тип\n");
        }
    }

    public boolean checkArg(String[] args) {
        if (args.length == 1) {
            try {
                Integer.parseInt(args[0]);
                return true;
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Ошибка: аргумент должен быть целым числом в диапазоне от " +
                        Integer.MIN_VALUE + " до " + Integer.MAX_VALUE + "\n");
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "remove_all_by_distance distance - удаляет все " +
                "элементы из коллекции, значения  поля distance которого эквивалентно заданному";
    }
}
