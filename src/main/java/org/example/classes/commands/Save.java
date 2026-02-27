package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;

import java.io.File;

import static org.example.classes.runner.Runner.*;

public class Save implements Command {

    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            if (args.length == 1) {
                File file = new File(args[0]);

                if (file.exists() && file.isDirectory()) {
                    managerInputOutput.writeLineIO("Это директория, а не файл\n", Colors.RED);
                } else {
                    managerReadWrite.writeCSV(args[0]);
                }
            } else {
                managerReadWrite.writeCSV(javaMetaspace);
            }
        } else {
            managerInputOutput.writeLineIO("Неправильное количество аргументов или неправильный тип\n", Colors.RED);
        }
    }

    public boolean checkArg(String[] args) {
        return args.length <= 1;
    }

    @Override
    public String toString() {
        return "save file_name - сохраняет коллекцию в файл";
    }
}