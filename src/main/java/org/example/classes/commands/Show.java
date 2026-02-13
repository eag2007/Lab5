package org.example.classes.commands;

import org.example.classes.Route;
import org.example.interfaces.Command;

import java.util.List;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class Show implements Command {
    public void executeCommand() {
        if (managerCollections.getCollectionsRoute().isEmpty()) {
            managerInputOutput.writeLineIO("Коллекция пуста\n");
        }
        for (Route route : managerCollections.getCollectionsRoute()) {
            String line = String.format("%-3s | %-20s | %-3s | %-3s | %-6s | %-6s | %-4s | %-6s | %-6s | %-4s | %-5s | %-25s",
                    route.getId(), route.getName(), route.getCoordinates().getX(), route.getCoordinates().getY(),
                    route.getCreationDate(), route.getFrom().getX(), route.getFrom().getY(), route.getFrom().getZ(),
                    route.getTo().getX(), route.getTo().getY(), route.getTo().getZ(), route.getDistance());
            managerInputOutput.writeLineIO(line + "\n");
        }
    }

    @Override
    public String toString() {
        return "show - выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
