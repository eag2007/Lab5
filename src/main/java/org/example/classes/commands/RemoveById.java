package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;

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
                        Integer.MIN_VALUE + " до " + Integer.MAX_VALUE + "\n", Colors.RED);
                return false;
            }
        }
        managerInputOutput.writeLineIO("Неправильное количество аргументов\n", Colors.RED);
        return false;
    }

    @Override
    public String toString() {
        return "remove_by_id id - удалить элемент из коллекции по его id";
    }
}