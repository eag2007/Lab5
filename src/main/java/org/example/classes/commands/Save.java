package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.*;

public class Save implements Command {
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            if (args.length == 1) {
                managerReadWrite.writeCSV(args[0]);
            } else {
                managerReadWrite.writeCSV(javaMetaspace);
            }
        } else {
            managerInputOutput.writeLineIO("Неправильное количество аргументов или неправильный тип\n", Colors.RED);
        }
    }

    public boolean checkArg(String[] args) {
        if (args.length <= 1) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "save file_name - сохраняет коллекцию в файл";
    }
}
