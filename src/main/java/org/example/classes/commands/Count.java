package org.example.classes.commands;

import org.example.classes.Route;
import org.example.enums.Colors;
import org.example.interfaces.Command;

import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class Count implements Command {
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            int count = 0;
            try {
                PriorityQueue<Route> routes = managerCollections.getCollectionsRoute();
                for (Route route : routes) {
                    if (route.getDistance() == Integer.parseInt(args[0])) {
                        count++;
                    }
                }
                managerInputOutput.writeLineIO("Количество элементов где distance = " + args[0] + " : " + count + "\n");
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Неправильный тип аргумента\n", Colors.RED);
            }
        } else {
            managerInputOutput.writeLineIO("Неправильное количество аргументов или их тип\n", Colors.RED);
        }
    }

    public boolean checkArg(String[] args) {
        if (args.length == 1) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "count {distance} - выводит количество элементов с полем distance равным данному";
    }
}
