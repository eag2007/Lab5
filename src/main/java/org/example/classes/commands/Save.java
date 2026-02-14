package org.example.classes.commands;

import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerInputOutput;
import static org.example.classes.runner.Runner.managerReadWrite;

public class Save implements Command {
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            managerReadWrite.writeCSV("/home/evgeniy/" +
                    "IdeaProjects/Lab5-Graddle/src/main/java/org/example/csv/collections.csv");
        } else {
            managerInputOutput.writeLineIO("Неправильное количество аргументов или неправильный тип\n");
        }
    }

    public boolean checkArg(String[] args) {
        if (args.length == 1) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "save file_name - сохраняет коллекцию в файл";
    }
}
