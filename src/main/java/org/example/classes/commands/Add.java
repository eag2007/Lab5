package org.example.classes.commands;

import org.example.classes.Route;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class Add implements Command {
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            Route element = new Route();
            managerCollections.addCollections(element);
        } else {
            managerInputOutput.writeLineIO("Неправильное количество элементов\n");
        }
    }

    public boolean checkArg(String[] args) {
        if (args.length == 0) {
            return true;
        }
        if (args.length == 1 || args[1] == "Route") {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "add - добавляет новый элемент в коллекцию";
    }
}
