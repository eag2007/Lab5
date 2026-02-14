package org.example.classes.commands;

import org.example.classes.Route;
import org.example.interfaces.Command;

import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class RemoveById implements Command {
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            Long id = Long.parseLong(args[0]);
            managerCollections.removeByIdCollections(id);
        }
    }

    public boolean checkArg(String[] args) {
        if (args.length == 1) {
            try {
                Integer.parseInt(args[0]);
                return true;
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Ошибка: аргумент должен быть целым числом в диапазоне от " +
                        Long.MIN_VALUE + " до " + Long.MAX_VALUE + "\n");
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "remove_by_id id - удалить элемент из коллекции по его id";
    }
}
