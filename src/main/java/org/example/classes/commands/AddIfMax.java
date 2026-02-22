package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.classes.Route;
import org.example.interfaces.Command;

import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.*;

public class AddIfMax implements Command {
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            Route newRoute = managerValidationData.validateFromInput();

            PriorityQueue<Route> routes = managerCollections.getCollectionsRoute();

            if (!routes.isEmpty()) {
                Route maxRoute = routes.stream()
                        .max(Route.COMPARATOR_COLLECTIONS)
                        .orElse(null);

                if (maxRoute != null && Route.COMPARATOR_COLLECTIONS.compare(newRoute, maxRoute) > 0) {
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
            managerInputOutput.writeLineIO("Неправильное количество аргументов\n");
        }
    }

    public boolean checkArg(String[] args) {
        if (args.length == 0) {
            return true;
        }
        if (args.length == 1 && args[0].equals("Route")) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "add_if_max {element} - добавляет новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента в коллекции";
    }
}