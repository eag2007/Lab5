package org.example.classes.commands;

import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerReadWrite;

public class Save implements Command {
    public void executeCommand() {
        managerReadWrite.writeCSV("/home/evgeniy/" +
                "IdeaProjects/Lab5-Graddle/src/main/java/org/example/csv/collections.csv");
    }

    @Override
    public String toString() {
        return "save file_name - сохраняет коллекцию в файл";
    }
}
