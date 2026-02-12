package org.example.classes.commands;

import org.example.classes.Route;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;

public class Add implements Command {
    public void executeCommand() {
        Route element = new Route();
        managerCollections.addCollections(element);
    }

    @Override
    public String toString() {
        return "add - добавляет новый элемент в коллекцию";
    }
}
