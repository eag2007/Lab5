package org.example.classes.commands;

import org.example.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class Update implements Command {
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            managerCollections.updateCollections(Long.parseLong(args[0]));
            managerInputOutput.writeLineIO("Элемент обновлён", Colors.GREEN);
        } else {
            managerInputOutput.writeLineIO("Неправильный порядок аргументов или их тип\n");
        }
    }

    public boolean checkArg(String[] args) {
        if (args.length == 2 && args[1].equals("Route")) {
            try {
                Long.parseLong(args[0]);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "update id {element} - обновляет значение элемента коллекции, id которого равен заданному";
    }
}
