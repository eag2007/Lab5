package org.example.classes.commands;

import org.example.classes.Route;
import org.example.interfaces.Command;

import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class AverageOfDistance implements Command {
    public void executeCommand() {
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
    }

    @Override
    public String toString() {
        return "average_of_distance - выводит среднее значение поля distance для всех элементов коллекции";
    }
}
