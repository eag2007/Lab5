package org.example.classes.commands;

import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class Clear implements Command {
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            managerCollections.clearCollections();
        } else {
            managerInputOutput.writeLineIO("Неправильное количество аргументов\n");
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
        return "clear - очищает коллекцию";
    }
}
