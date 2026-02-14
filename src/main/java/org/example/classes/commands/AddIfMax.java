package org.example.classes.commands;

import org.example.classes.Route;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;

public class AddIfMax implements Command {
    public void executeCommand(String[] args) {
        Route route = new Route();
    }

    public boolean checkArg(String[] args) {
        if (args.length == 1) {
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
