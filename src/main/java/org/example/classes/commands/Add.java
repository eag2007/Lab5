package org.example.classes.commands;

import org.example.Colors;
import org.example.classes.Route;
import org.example.classes.managers.ManagerValidationData;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.*;

public class Add implements Command {
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            if (!managerInputOutput.isScriptMode()) {
                managerCollections.addCollections(managerValidationData.validateFromInput());
                managerInputOutput.writeLineIO("Элемент создан", Colors.GREEN);
            } else {
                Route element = managerValidationData.validateFromFile();
                if (element != null) {
                    managerCollections.addCollections(element);
                    managerInputOutput.writeLineIO("Элемент создан", Colors.GREEN);
                } else {
                    managerInputOutput.writeLineIO("Ошибка: невозможно создать элемент\n");
                    managerInputOutput.writeLineIO("[Элемент не создан]\n");
                }
            }
        } else {
            managerInputOutput.writeLineIO("Неправильное количество элементов\n");
        }
    }

    public boolean checkArg(String[] args) {
        if (args.length == 0) {
            return true;
        }
        if (args.length == 1 && args[0].equals("Route")) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "add - добавляет новый элемент в коллекцию";
    }
}
