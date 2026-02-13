package org.example.classes.commands;

import org.example.classes.Route;
import org.example.interfaces.Command;

import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class FilterLessThanDistance implements Command {
//    public void executeCommand(Integer distance) {
//        PriorityQueue<Route> routes = managerCollections.getCollectionsRoute();
//        for (Route route : routes) {
//            if (route.getDistance() < distance) {
//                String line = String.format("%-3s | %-20s | %-3s | %-3s | %-6s | %-6s | %-4s | %-6s | %-6s | %-4s | %-5s | %-25s",
//                            route.getId(), route.getName(), route.getCoordinates().getX(), route.getCoordinates().getY(),
//                            route.getCreationDate(), route.getFrom().getX(), route.getFrom().getY(), route.getFrom().getZ(),
//                            route.getTo().getX(), route.getTo().getY(), route.getTo().getZ(), route.getDistance());
//                managerInputOutput.writeLineIO(line + "\n");
//            }
//        }
//    }

    @Override
    public String toString() {
        return "filter_less_than_distance - выводит элементы значения поля distance которых меньше заданного";
    }
}
