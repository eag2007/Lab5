package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class RemoveFirst implements Command {

    public void executeCommand(String[] args) {
        if (checkArg(args)){
            managerCollections.removeFirstCollections();
        } else {
            managerInputOutput.writeLineIO("Неверное количество аргументов\n", Colors.RED);
        }
    }

    public boolean checkArg(String[] args) {
        return args.length == 0;
    }

    @Override
    public String toString() {
        return "remove_first  - удаляет первый элемент из коллекции";
    }
}