package org.example.classes.commands;

import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class Show implements Command {
    public void executeCommand() {
        managerInputOutput.writeLineIO(managerCollections.getCollectionsRoute() + "\n");
    }

    @Override
    public String toString() {
        return "show - выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
