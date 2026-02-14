package org.example.classes.commands;

import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class RemoveFirst implements Command {
    public void executeCommand(String[] args) {
        if (checkArg(args)){
            managerCollections.removeFirstCollections();
        } else {
            managerInputOutput.writeLineIO("Неверное количество аргументов\n");
        }
    }

    public boolean checkArg(String[] args) {
        if (args.length == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "remove_first  - удаляет первый элемент из коллекции";
    }
}
